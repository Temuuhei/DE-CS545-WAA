package websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Stack;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

	public int calc (String expression) {
		Stack<Integer> stk = new Stack<>();
		int result = 0;
		int number = 0;
		int sign = 1;
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (Character.isDigit(c)) {
				number = 10 * number + (int)(c - '0');
			} else if (c == '-') {
				result += sign * number;
				number = 0;
				sign = -1;
			} else if (c == '+') {
				result += sign * number;
				number = 0;
				sign = 1;
			} else if (c == '(') {
				stk.push(result);
				stk.push(sign);
				sign =  1;
				result = 0;
			} else if (c == ')') {
				result += sign * number;
				number = 0;
				result *= stk.pop();
				result += stk.pop();
			}
		}

		if (number != 0 ) result += sign * number;

		return result;
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {
		System.out.println("got message"+ message);
		session.sendMessage(new TextMessage("Hi, " + message.getPayload() + " = " + calc( message.getPayload())));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		System.out.println("Connected");
		//send message back to the client
		session.sendMessage(new TextMessage("Connected !"));

//		MyThread myThread = new MyThread(session);
//		Thread t = new Thread(myThread);
//		t.start();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("Closed");
	}

}