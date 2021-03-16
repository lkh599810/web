package review;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import java2_9.loginController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class memberlistcontroller implements Initializable {
	
	
	    @FXML
	    private Button btnlogout;

	    @FXML
	    private Label lbllogid;

	    @FXML
	    private Button btnprofile;

	    @FXML
	    private Button btnmodifi;

	    @FXML
	    private Button btndelete;

	    @FXML
	    private Button btnmemberlist;

	    @FXML
	    private TableView<Member> membertable;

	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			DAO dao = DAO.getDB();
			ObservableList <Member> memberlist = dao.getlistmember();
			
			TableColumn tc = membertable.getColumns().get(0);
			tc.setCellValueFactory( new PropertyValueFactory("id"));
			
			tc = membertable.getColumns().get(1);
			tc.setCellValueFactory( new PropertyValueFactory("name"));
			
			tc = membertable.getColumns().get(2);
			tc.setCellValueFactory( new PropertyValueFactory("lastdate"));
			
			membertable.setItems(memberlist);	
			
		}
	
	// 로그아웃 버튼을 클릭했을때 
	public void logoutAction( ) {
		lbllogid.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load( getClass().getResource("login.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	// 회원탈퇴 버튼을 클릭했을때 
	public void deleteAction( ActionEvent e   ) {
		
		// 삭제 여부 물어보기 
		Alert alert = new Alert( AlertType.CONFIRMATION );
		
		alert.setContentText(" 정말 탈퇴 하시겠습니까? ");
		alert.setTitle("알림");
		alert.setHeaderText(" 회원탈퇴 ");
		// 확인 또는 취소 버튼을 눌렀을때
		Optional<ButtonType> bresult = 	alert.showAndWait();
	
		if( bresult.get() == ButtonType.OK ) { // 확인 눌렀을때 
			// 삭제 db 메소드 호출 
			DAO dao = DAO.getDB();
			int result =  dao.delmember(Logincontroller.getinstance().userid());
				
			if( result == 1 ) {
				Alert alert2 = new Alert( AlertType.INFORMATION );
				
				alert2.setContentText(" 정상적으로 탈퇴 되었습니다 ");
				alert2.setTitle("알림");
				alert2.setHeaderText(" 회원탈퇴 ");
				alert2.showAndWait();
				logoutAction();
			}
			else {
				Alert alert2 = new Alert( AlertType.INFORMATION );
				
				alert2.setContentText(" 데이터베이스 오류 \n 관리자에게 문의바랍니다 ");
				alert2.setTitle("알림");
				alert2.setHeaderText(" 회원탈퇴 ");
				alert2.showAndWait();
			}
		}else { // 취소 버튼을 눌렀을때 
			return;
		}
	}
	// 회원목록 버튼 클릭했을때
	public void memberlistAction( ActionEvent e   ) {
	
		try {
			// 새로운 스테이지 X => 기존 스테이지를 가져와서 변경 [ 레이아웃 전환 ]
		Stage stage = (Stage)btnlogout.getScene().getWindow();
						// 해당버튼이 존재하는 씬의 윈도우창을 스테이지에 저장 
		Parent parent = FXMLLoader.load( getClass().getResource("memberlist.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	// 회원수정 버튼 클릭했을때
	public void membermodify() {
		
		try {
			// 새로운 스테이지 X => 기존 스테이지를 가져와서 변경 [ 레이아웃 전환 ]
		Stage stage = (Stage)btnlogout.getScene().getWindow();
						// 해당버튼이 존재하는 씬의 윈도우창을 스테이지에 저장 
		Parent parent = FXMLLoader.load( getClass().getResource("memberupdate.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}

	}

	// 회원정보 버튼을 클릭했을때 
	public void memberAction(  ActionEvent e ) {
		try {
				// 새로운 스테이지 X => 기존 스테이지를 가져와서 변경 [ 레이아웃 전환 ]
			Stage stage = (Stage)btnlogout.getScene().getWindow();
							// 해당버튼이 존재하는 씬의 윈도우창을 스테이지에 저장 
			Parent parent = FXMLLoader.load( getClass().getResource("member.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	
  
}
