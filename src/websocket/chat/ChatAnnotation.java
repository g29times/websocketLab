/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package websocket.chat;

import java.io.IOException;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import util.HTMLFilter;
import work.Customer;
import work.Excel.AgentGet;
import work.Excel.AgentSet;
import work.Excel.ExcelWorkLoad;

@ServerEndpoint(value = "/websocket/chat")
public class ChatAnnotation implements Runnable {

    private static final Log log = LogFactory.getLog(ChatAnnotation.class);

    private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatAnnotation> connections =
            new CopyOnWriteArraySet<>();

    private final String nickname;
    private Session session;

    public ChatAnnotation() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }

    /*********************************************/

    // Ä£ÄâAgentGet
    private ExcelWorkLoad workLoad;
    private AgentSet agentSet;

    // M2
    public void timer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                broadcast("CountNum: " + workLoad.getCountNum().toString());
                if (agentSet != null && agentSet.getTotal() == workLoad.getCountNum())
                    timer.cancel();
            }
        }, 0, 1000);
    }

    public void test() {
        workLoad = new ExcelWorkLoad();
        workLoad.setCountNum(0);

        agentSet = new AgentSet(workLoad, this);
        Thread setThread = new Thread(agentSet);
        setThread.start();

        // M1
//        Thread getThread = new Thread(this);
//        getThread.start();

        // M2
        timer();
    }

    /**********
     * M1
     **********/

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            getCount(workLoad);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }

    public void getCount(ExcelWorkLoad workLoad) {
        broadcast(workLoad.getCountNum().toString());
//        System.out.println(workLoad.getCountNum());
    }

    /*********************************************/


    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "has joined.");
        broadcast(message);
    }

    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s",
                nickname, "has disconnected.");
        broadcast(message);
    }

    @OnMessage
    public void incoming(String message) {
        // Never trust the client
        String filteredMessage = String.format("%s: %s",
                nickname, HTMLFilter.filter(message.toString()));
        broadcast(filteredMessage);
        test();
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }

    private static void broadcast(String msg) {
        for (ChatAnnotation client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                log.debug("Chat Error: Failed to send message to client", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(message);
            }
        }
    }
}
