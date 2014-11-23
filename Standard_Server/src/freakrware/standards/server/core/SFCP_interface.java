package freakrware.standards.server.core;

public interface SFCP_interface {

	// Socket Commandos
		static final String CONNECTION_CLOSE = "Connection_Close";
		static final String CONNECTION_REQUEST = "Connection_Request";
		static final String CONNECTION_ACCEPTED = "Connection_Accepted";
		static final String CONNECTION_KEEP = "Connection_Keep";
		static final String DB_UPDATE_FULL = "DB_Full_Update";
		static final String ACTION_COMPLETE = "Action_Complete";
		static final String ERROR = "Error";
		static final String FILE_COMPLETE = "File_Complete";
		static final String REQUEST_DB_FILES = "Request_DB_Files";
		static final String REQUEST_URL = "Request_Url";
		static final String REQUEST_ADD_USER = "Request_add_User";
		static final String REQUEST_REMOVE_USER = "Request_remove_User";
		static final String REQUEST_ADD_MESSAGE = "Request_add_Message";
		static final String REQUEST_NEW_MESSAGES_FROM_BOARD = "Request_new_Messages_from_Board";
		static final String NO_NEW_MESSAGES_FROM_BOARD = "No_new_Messages_from_Board";
		static final String MESSAGE_ADDED = "Message_added";
		static final String MESSAGE_NOT_ADDED = "Message_not_added";
		static final String ALL_MESSAGES_RECEIVED = "All_Messages_received";
		static final String MESSAGE_RECEIVE_ERROR = "Messages_Receive_Error";
		static final String OPEN_LINK = "Open_Link";
		static final String SENDER_UNKNOWN = "Sender_Unknown";
		static final String RECEIVER_UNKNOWN = "Receiver_Unknown";
		static final String USER_EXISTS = "User_exists";
		static final String USER_NOT_EXISTS = "User_not_exists";
		static final String USER_ADDED = "User_added";
		static final String USER_NOT_ADDED = "User_not_added";
		static final String USER_REMOVED = "User_removed";
		static final String USER_NOT_REMOVED = "User_not_removed";
		
	
}
