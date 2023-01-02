package com.example.linecloneback.websocket;

//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class WebSocketHandler extends TextWebSocketHandler {
//
//    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
////    //websocketsession은 websocket이 연결될 때 생기는 연결 정보를 담고 있는 객체
//
////    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
////        System.out.println(message);
////        System.out.println(message.getPayload());
////    }
//
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        JSONObject jsonObject = new JSONObject(payload);
//        for(WebSocketSession s : sessions) {
//            s.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + "!How may I help you?"));
//        }
//    }
//
////    @Override
////    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
////        System.out.println("afterConnectionEstablished:" + session.toString());
////    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) {
//        sessions.add(session);
//    }
//
////    @Override
////    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
////        super.afterConnectionClosed(session, status);
////    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessions.remove(session);
//    }
//}