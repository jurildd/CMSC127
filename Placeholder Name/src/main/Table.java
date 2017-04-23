import javafx.beans.property.SimpleStringProperty;

public class Table {
	private final SimpleStringProperty StudentName;
	private final SimpleStringProperty StudentID;
	private final SimpleStringProperty Program;
	private final SimpleStringProperty LogIn;
	
	public Table(String sn, String sID, String prog, String log) {
		this.StudentName = new SimpleStringProperty(sn);
		this.StudentID = new SimpleStringProperty(sID);
		this.Program = new SimpleStringProperty(prog);
		this.LogIn = new SimpleStringProperty(log);	
	}

	public String getStudentName() {
		return StudentName.get();
	}

	public String getStudentID() {
		return StudentID.get();
	}

	public String getProgram() {
		return Program.get();
	}

	public String getLogIn() {
		return LogIn.get();
	}
	
	public void setStudentName(String sn) {
		StudentName.set(sn);
	}
	
	public void setStudentID(String sID) {
		StudentID.set(sID);
	}
	
	public void setProgram(String prog) {
		Program.set(prog);
	}
	
	public void setLogIn(String log) {
		LogIn.set(log);
	}
}
