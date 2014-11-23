package freakrware.standards.server.core;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class SFCP implements SFCP_interface{

	private String line;
	private BufferedReader input;
	private PrintWriter output;
	private String[] arguments;
	private Socket server;
	private String command;
	
	public SFCP(String line, BufferedReader input, PrintWriter output, String[] arguments, Socket server, String command) throws IOException {
		this.line = line;
		this.input = input;
		this.output = output;
		this.arguments = arguments;
		this.server = server;
		this.command = command;
		client();
	}
	public SFCP(String line, BufferedReader input, PrintWriter output) throws IOException {
		this.line = line;
		this.input = input;
		this.output = output;
		server();
	}

	private void client() throws IOException {
		switch (line) {
		case CONNECTION_CLOSE:
			output.println(line);
			server.shutdownInput();
			server.shutdownOutput();
			server.close();
			break;
//		case ONLINESTATUS:
//			for(int x=0;x < arguments.length;x++){
//				output.println(arguments[x]);
//			}
//			break;
		case REQUEST_URL:
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
    		break;
		case REQUEST_NEW_MESSAGES_FROM_BOARD:
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
			line = input.readLine();
			System.out.println(line);
			if(line.equals(NO_NEW_MESSAGES_FROM_BOARD)){
				output.println(CONNECTION_CLOSE);
				break;
			}
			else{
				String[][] messages = new String[Integer.parseInt(line)/3][3];
				for(int x=0;x< (Integer.parseInt(line)/3);x++){
					messages[x][0] = input.readLine();
					System.out.println(messages[x][0]);
					messages[x][1] = input.readLine();
					System.out.println(messages[x][1]);
					messages[x][2] = input.readLine();
					System.out.println(messages[x][2]);
				}
				output.println(ALL_MESSAGES_RECEIVED);
			}
			break;
		case REQUEST_ADD_MESSAGE:
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
    		break;
		case REQUEST_ADD_USER:
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
    		break;
		case REQUEST_REMOVE_USER:
			for(int x=0;x < arguments.length;x++){
				output.println(arguments[x]);
			}
    		break;
		case CONNECTION_ACCEPTED:
			output.println(command);
			break;
		case ACTION_COMPLETE:
			output.println(CONNECTION_CLOSE);
			break;
		default:
			output.println(CONNECTION_CLOSE);
			
		}
		
	}
	
	private void server() throws IOException {
		switch(line){
    	case CONNECTION_CLOSE:
    		output.println(CONNECTION_CLOSE);
    		output.close();
    		input.close();
			break;
//    	case ONLINESTATUS:
//    		output.println(line);
//    		if(DB.user_online(input.readLine(),input.readLine(),input.readLine())){
//    			output.println(ACTION_COMPLETE);
//    		}
//    		else{
//    			output.println(ERROR);
//    		}
//    		break;
    	case CONNECTION_REQUEST:
    		output.println(CONNECTION_ACCEPTED);
    		break;
//    	case REQUEST_ADD_MESSAGE:
//    		output.println(REQUEST_ADD_MESSAGE);
//    		String stringsentfrom = input.readLine();
//    		String stringsentto = input.readLine();
//    		String message = input.readLine();
//    		int sentfrom;
//    		int sentto;
//    		if(DB.user_exists(stringsentfrom)== 0){
//    			output.println(SENDER_UNKNOWN);
//    			break;
//    		}else
//    		{
//    			sentfrom = DB.user_exists(stringsentfrom);
//    		}
//    		if(DB.user_exists(stringsentto)== 0){
//    			output.println(RECEIVER_UNKNOWN);
//    			break;
//    		}else
//    		{
//    			sentto = DB.user_exists(stringsentto);
//    		}
//    		if(DB.message_exists(message) == 0){
//    			DB.message_add(message);
//    			if(DB.messageboard_add(sentfrom,sentto,DB.message_exists(message))){
//    				output.println(MESSAGE_ADDED);
//    			}else{
//    				output.println(MESSAGE_NOT_ADDED);
//    			}
//    		}else
//    		{
//    			if(DB.messageboard_add(sentfrom,sentto,DB.message_exists(message))){
//    				output.println(MESSAGE_ADDED);
//    			}else{
//    				output.println(MESSAGE_NOT_ADDED);
//    			}
//    		}
//    		break;
//    	case REQUEST_NEW_MESSAGES_FROM_BOARD:
//    		output.println(REQUEST_NEW_MESSAGES_FROM_BOARD);
//    		line = input.readLine();
//    		System.out.println(line);
//			if(DB.user_exists(line) != 0){
//				String[][] messagefromboard = DB.get_new_messages(DB.user_exists(line));
//				if(messagefromboard.length > 0 ){
//					
//					output.println(messagefromboard.length*(messagefromboard[0].length-1));
//					for(int x=0;x<messagefromboard.length;x++){
//						output.println(messagefromboard[x][0]);
//						output.println(messagefromboard[x][1]);
//						output.println(messagefromboard[x][2]);
//					}
//						line = input.readLine();
//						System.out.println(line);
//						if (line.equals(ALL_MESSAGES_RECEIVED)){
//							output.println(ACTION_COMPLETE);
//							for(int y=0;y<messagefromboard.length;y++){
//								DB.messages_senttrue(messagefromboard[y][3]);
//							}
//							
//						}
//						else
//						{
//							output.println(MESSAGE_RECEIVE_ERROR);
//						}
//					}
//				else
//				{
//					output.println(NO_NEW_MESSAGES_FROM_BOARD);
//				}
//			}
//			else{
//				output.println(USER_NOT_EXISTS);
//				}
//			
//			break;
//    	case REQUEST_ADD_USER:
//    		output.println(REQUEST_ADD_USER);
//    		line = input.readLine();
//    		System.out.println(line);
//			if(DB.user_exists(line) != 0){
//				output.println(USER_EXISTS);
//			}
//			else{
//				if(DB.user_add(line)){
//					output.println(USER_ADDED);
//				}else
//				{
//					output.println(USER_NOT_ADDED);
//				}
//				
//			}
//			break;
//    	case REQUEST_REMOVE_USER:
//    		output.println(REQUEST_REMOVE_USER);
//    		line = input.readLine();
//    		System.out.println(line);
//			if(DB.user_exists(line) != 0){
//				System.out.println(USER_EXISTS);
//				if(DB.user_remove(line)){
//					output.println(USER_REMOVED);
//				}else
//				{
//					output.println(USER_NOT_REMOVED);
//				}
//			}
//			else{
//				output.println(USER_NOT_EXISTS);
//			}
//			break;	
    	case OPEN_LINK:
    		output.println(REQUEST_URL);
    		line = input.readLine();
    		System.out.println(line);
			URI uri = null;
			try {
				uri = new URI(line);
				if (Desktop.isDesktopSupported()) {
        		     Desktop.getDesktop().browse(uri);
				}
        		else { 
        		    System.out.println("Kein Desktop gefunden !");
        		}
			} catch (URISyntaxException e1) {
				System.out.println(e1.getMessage());
			}
			output.println(ACTION_COMPLETE);
    		break;
    	default :
    		output.println(CONNECTION_CLOSE);
    		output.close();
    		input.close();
			break;
    	}
		
	}
}
