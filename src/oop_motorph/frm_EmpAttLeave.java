
package oop_motorph;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;

public class frm_EmpAttLeave extends javax.swing.JFrame {

    
    public frm_EmpAttLeave() {
        initComponents();
        displayLoggedInEmployeeAttendance();
        
        //JBF: Sets the windown to center and disable resizable
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Employee Attendance");
    
    EmpDetails employee = EmpUserSession.getInstance().getCurrentUser();
    if (employee !=null)  {
        String role = CSVHandler.loadCredentials().get(employee.getEmpID())[1];
        setRoleBasedAccess(role);
    } else {
        JOptionPane.showMessageDialog(this, "User not logged in!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setRoleBasedAccess(String role) {
    // Default: Disable all buttons
    btn_MyRecords.setEnabled(true);
    btn_LeaveMgt.setEnabled(false);
    btn_SalaryAndStatutory.setEnabled(false);
    btn_Profile.setEnabled(true);
    btn_Logout.setEnabled(false);
    btn_EmpRecords.setEnabled(false);
    btn_PayrollSummary.setEnabled(false);
    btn_LeaveApproval.setEnabled(false);
    btn_LeaveRequest.setEnabled(false);

    // Enable buttons based on role (case-insensitive)
    switch (role.toUpperCase()) {
        case "EMPLOYEE":
            // Employees can access:
            btn_MyRecords.setEnabled(true);
            btn_SalaryAndStatutory.setEnabled(true);
            btn_Profile.setEnabled(true);
            btn_Logout.setEnabled(true);
            btn_PayrollSummary.setEnabled(true);
            btn_LeaveRequest.setEnabled(true);
            break;
        case "HR":
            // HR can access:
            btn_MyRecords.setEnabled(true);
            btn_SalaryAndStatutory.setEnabled(true);
            btn_Profile.setEnabled(true);
            btn_EmpRecords.setEnabled(true);
            btn_Logout.setEnabled(true);
            btn_PayrollSummary.setEnabled(true);

            btn_LeaveApproval.setEnabled(true);
            btn_LeaveRequest.setEnabled(true);
            break;
        case "FINANCE":
            // Finance can access:
            btn_MyRecords.setEnabled(true);
            btn_SalaryAndStatutory.setEnabled(true);
            btn_Profile.setEnabled(true);
            btn_PayrollSummary.setEnabled(true);
            btn_Logout.setEnabled(true);

            btn_LeaveRequest.setEnabled(true);
            btn_LeaveApproval.setEnabled(true);
            break;
        case "DEPTHEAD":
            // Dept-Head can access:
            btn_SalaryAndStatutory.setEnabled(true);
            btn_Logout.setEnabled(true);
            btn_PayrollSummary.setEnabled(true);
            btn_LeaveApproval.setEnabled(true);
            btn_LeaveRequest.setEnabled(true);
            break;
        case "FINANCE TL":
            // Finance TL can access:
            btn_MyRecords.setEnabled(true);
            btn_SalaryAndStatutory.setEnabled(true);
            btn_Profile.setEnabled(true);
            btn_PayrollSummary.setEnabled(true);
            btn_Logout.setEnabled(true);
            btn_LeaveApproval.setEnabled(true);
            btn_LeaveRequest.setEnabled(true);
            btn_EmpRecords.setEnabled(true);
            break;
        case "PAYROLL MANAGER":
            // Payroll Manager can access:
            btn_MyRecords.setEnabled(true);
            btn_SalaryAndStatutory.setEnabled(true);
            btn_Profile.setEnabled(true);
            btn_PayrollSummary.setEnabled(true);
            btn_Logout.setEnabled(true);
            btn_LeaveApproval.setEnabled(true);
            btn_LeaveRequest.setEnabled(true);
            btn_EmpRecords.setEnabled(true);
            break;
        case "ACCOUNTING":
            // Accounting can access:
            btn_MyRecords.setEnabled(true);
            btn_SalaryAndStatutory.setEnabled(true);
            btn_Profile.setEnabled(true);
            btn_PayrollSummary.setEnabled(true);
            btn_Logout.setEnabled(true);
            btn_LeaveMgt.setEnabled(true);
            btn_LeaveRequest.setEnabled(true);
            btn_LeaveApproval.setEnabled(true);
            break;
        case "ADMIN":
            // Admin can access:
            btn_MyRecords.setEnabled(true);
            btn_SalaryAndStatutory.setEnabled(true);
            btn_Profile.setEnabled(true);
            btn_PayrollSummary.setEnabled(true);
            btn_Logout.setEnabled(true);
            btn_EmpRecords.setEnabled(true);
            btn_LeaveMgt.setEnabled(true);
            btn_LeaveRequest.setEnabled(true);
            btn_LeaveApproval.setEnabled(true);
            break;
        default:
            JOptionPane.showMessageDialog(this, "Invalid role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
            break;
    }
}
    
// To display the data of the logged in User
    private void displayLoggedInEmployeeAttendance() {
    List<EmpAttLeave> attendanceList = CSVHandler.getAttendanceData();
    DefaultTableModel model = (DefaultTableModel) tbl_Attendance.getModel();
  
    //to disable tbl edit
    tbl_Attendance.setDefaultEditor(Object.class, null);
    
    // Clear existing data
    model.setRowCount(0);

    // Get the currently logged-in employee
    EmpDetails employee = EmpUserSession.getInstance().getCurrentUser();
    if (employee == null) {
        JOptionPane.showMessageDialog(this, "User not logged in!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String loggedInEmpID = employee.getEmpID(); // Get Employee ID

    // Filter and display only the logged-in employee's records
    for (EmpAttLeave att : attendanceList) {
        if (att.getEmpID().equals(loggedInEmpID)) {
            model.addRow(new Object[]{
                att.getEmpID(), att.getFirstName(), att.getLastName(), att.getEmployeeStatus(),
                att.getPosition(), att.getImmediateSupervisor(), att.getAttDateFrom(), att.getAttDateTo(), 
                att.getTimeIn(), att.getTimeOut(), att.getHoursWorked(), att.getDuration(), 
                att.getAttendanceType(), att.getAttendanceStatus(), att.getVlCount(), 
                att.getVlUsed(), att.getVlBalance(), att.getSlCount(), att.getSlUsed(), att.getSlBalance()
            });
        }
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_LeaveRequest = new javax.swing.JButton();
        btn_LeaveApproval = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Attendance = new javax.swing.JTable();
        txt_vlCount = new javax.swing.JTextField();
        txt_vlUsed = new javax.swing.JTextField();
        txt_vlBal = new javax.swing.JTextField();
        txt_slCount = new javax.swing.JTextField();
        txt_slUsed = new javax.swing.JTextField();
        txt_slBal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_Profile = new javax.swing.JButton();
        btn_LeaveMgt = new javax.swing.JButton();
        btn_SalaryAndStatutory = new javax.swing.JButton();
        btn_PayrollSummary = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btn_MyRecords = new javax.swing.JButton();
        btn_EmpRecords = new javax.swing.JButton();
        btn_Logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));

        btn_LeaveRequest.setBackground(new java.awt.Color(0, 0, 204));
        btn_LeaveRequest.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_LeaveRequest.setForeground(new java.awt.Color(255, 255, 255));
        btn_LeaveRequest.setText("Leave Request");
        btn_LeaveRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LeaveRequestActionPerformed(evt);
            }
        });

        btn_LeaveApproval.setBackground(new java.awt.Color(0, 0, 204));
        btn_LeaveApproval.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_LeaveApproval.setForeground(new java.awt.Color(255, 255, 255));
        btn_LeaveApproval.setText("Leave Approval");
        btn_LeaveApproval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LeaveApprovalActionPerformed(evt);
            }
        });

        tbl_Attendance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "First Name", "Last Name", "Status", "Position", "Immediate Supervisor", "Date From", "Date To", "Time In", "Time Out", "Hours Worked", "Duration", "AttendanceType", "AttendanceStatus", "VL Count", "VL Used", "VL Balance", "SL Count", "SL Used", "SL Balance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_Attendance);

        txt_vlCount.setEditable(false);

        txt_vlUsed.setEditable(false);

        txt_vlBal.setEditable(false);

        txt_slCount.setEditable(false);

        txt_slUsed.setEditable(false);

        txt_slBal.setEditable(false);

        jLabel6.setText("VL Count:");

        jLabel7.setText("VL Used:");

        jLabel8.setText("VL Balance:");

        jLabel9.setText("SL Count:");

        jLabel10.setText("SL Used:");

        jLabel11.setText("SL Balance:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_LeaveRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_vlBal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(txt_vlUsed, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_vlCount))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_LeaveApproval, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_slUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_slCount, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_slBal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(173, 173, 173))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LeaveRequest)
                    .addComponent(btn_LeaveApproval))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_slCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_vlCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txt_slUsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_vlUsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_slBal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_vlBal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel2.setText("MotorPH");

        jPanel3.setBackground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );

        btn_Profile.setBackground(new java.awt.Color(51, 51, 255));
        btn_Profile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Profile.setForeground(new java.awt.Color(255, 255, 255));
        btn_Profile.setText("Profile");
        btn_Profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProfileActionPerformed(evt);
            }
        });

        btn_LeaveMgt.setBackground(new java.awt.Color(51, 51, 255));
        btn_LeaveMgt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_LeaveMgt.setForeground(new java.awt.Color(255, 255, 0));
        btn_LeaveMgt.setText("Attendance & Leave");
        btn_LeaveMgt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LeaveMgtActionPerformed(evt);
            }
        });

        btn_SalaryAndStatutory.setBackground(new java.awt.Color(51, 51, 255));
        btn_SalaryAndStatutory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_SalaryAndStatutory.setForeground(new java.awt.Color(255, 255, 255));
        btn_SalaryAndStatutory.setText("Salary & Statutory");
        btn_SalaryAndStatutory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalaryAndStatutoryActionPerformed(evt);
            }
        });

        btn_PayrollSummary.setBackground(new java.awt.Color(51, 51, 255));
        btn_PayrollSummary.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_PayrollSummary.setForeground(new java.awt.Color(255, 255, 255));
        btn_PayrollSummary.setText("Payroll Summary");
        btn_PayrollSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PayrollSummaryActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(245, 28, 71));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1032, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        btn_MyRecords.setBackground(new java.awt.Color(51, 51, 255));
        btn_MyRecords.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_MyRecords.setForeground(new java.awt.Color(255, 255, 0));
        btn_MyRecords.setText("My Record");
        btn_MyRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MyRecordsActionPerformed(evt);
            }
        });

        btn_EmpRecords.setBackground(new java.awt.Color(51, 51, 255));
        btn_EmpRecords.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_EmpRecords.setForeground(new java.awt.Color(255, 255, 255));
        btn_EmpRecords.setText("Employee Records");
        btn_EmpRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EmpRecordsActionPerformed(evt);
            }
        });

        btn_Logout.setBackground(new java.awt.Color(51, 51, 255));
        btn_Logout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Logout.setForeground(new java.awt.Color(255, 255, 255));
        btn_Logout.setText("Logout");
        btn_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LogoutActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oop_motorph/img/img_myrecord.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oop_motorph/img/img_emprecords.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oop_motorph/img/img_logout.png"))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel5.setText("Version 1.30");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5))
                                .addGap(67, 67, 67))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_EmpRecords)
                                    .addComponent(btn_MyRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(55, 55, 55)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_Profile, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btn_LeaveMgt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btn_SalaryAndStatutory, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_PayrollSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Profile)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LeaveMgt)
                    .addComponent(btn_SalaryAndStatutory)
                    .addComponent(btn_PayrollSummary))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_MyRecords)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_EmpRecords)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Logout))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("Profile");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_PayrollSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PayrollSummaryActionPerformed
        
        new frm_EmpPayroll().setVisible(true);
        this.dispose();
         
    }//GEN-LAST:event_btn_PayrollSummaryActionPerformed

    private void btn_SalaryAndStatutoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalaryAndStatutoryActionPerformed
        
        new frm_EmpSalary().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btn_SalaryAndStatutoryActionPerformed

    private void btn_LeaveMgtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LeaveMgtActionPerformed
        
        new frm_EmpAttLeave().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btn_LeaveMgtActionPerformed

    private void btn_ProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProfileActionPerformed
        
        new frm_EmpProfile().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btn_ProfileActionPerformed

    private void btn_MyRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MyRecordsActionPerformed
        new frm_EmpProfile().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btn_MyRecordsActionPerformed

    private void btn_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LogoutActionPerformed
        
        CSVHandler.handleLogout(this); 
        
    }//GEN-LAST:event_btn_LogoutActionPerformed

    private void btn_LeaveRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LeaveRequestActionPerformed
    int selectedRow = tbl_Attendance.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select any row.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // Debugging: Check the number of columns
        int columnCount = tbl_Attendance.getColumnCount();
        System.out.println("Total Columns: " + columnCount);

        if (columnCount < 14) { // Adjust based on actual column count in the table
            JOptionPane.showMessageDialog(this, "Table column count mismatch. Expected at least 14 columns.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Define date and time formatters
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // Adjust format if needed

        // Retrieve JTextField values
        String empID = tbl_Attendance.getValueAt(selectedRow, 0).toString();
        String firstName = tbl_Attendance.getValueAt(selectedRow, 1).toString();
        String lastName = tbl_Attendance.getValueAt(selectedRow, 2).toString();
        String empStatus = tbl_Attendance.getValueAt(selectedRow, 3).toString();
        String position = tbl_Attendance.getValueAt(selectedRow, 4).toString();
        String immSupervisor = tbl_Attendance.getValueAt(selectedRow, 5).toString();

        // Convert String to LocalDate
        LocalDate attdateFrom = LocalDate.parse(tbl_Attendance.getValueAt(selectedRow, 6).toString(), dateFormatter);
        LocalDate attdateTo = LocalDate.parse(tbl_Attendance.getValueAt(selectedRow, 7).toString(), dateFormatter);

        // Convert String to LocalTime
        LocalTime timeIn = LocalTime.parse(tbl_Attendance.getValueAt(selectedRow, 8).toString(), timeFormatter);
        LocalTime timeOut = LocalTime.parse(tbl_Attendance.getValueAt(selectedRow, 9).toString(), timeFormatter);

        // Retrieve numeric values safely
        double hoursWorked = parseDoubleSafe(tbl_Attendance.getValueAt(selectedRow, 10));
        double duration = parseDoubleSafe(tbl_Attendance.getValueAt(selectedRow, 11));

        // Retrieve JComboBox values correctly
        String attendanceType = tbl_Attendance.getValueAt(selectedRow, 12).toString();
        String attendanceStatus = tbl_Attendance.getValueAt(selectedRow, 13).toString();

        // Retrieve numeric values for leave balances
        double vlCount = parseDoubleSafe(tbl_Attendance.getValueAt(selectedRow, 14));
        double vlUsed = parseDoubleSafe(tbl_Attendance.getValueAt(selectedRow, 15));
        double vlBal = parseDoubleSafe(tbl_Attendance.getValueAt(selectedRow, 16));
        double slCount = parseDoubleSafe(tbl_Attendance.getValueAt(selectedRow, 17));
        double slUsed = parseDoubleSafe(tbl_Attendance.getValueAt(selectedRow, 18));
        double slBal = parseDoubleSafe(tbl_Attendance.getValueAt(selectedRow, 19));

        // Create the employee attendance object
        EmpAttLeave employeeAttendance = new EmpAttLeave(
            empID, firstName, lastName, empStatus, position, immSupervisor,
            attdateFrom, attdateTo, timeIn, timeOut, hoursWorked, duration,
            attendanceType, attendanceStatus, vlCount, vlUsed, vlBal,
            slCount, slUsed, slBal
        );

        // Open the attendance request form and pass data
        frm_EmpAddAttLeave frm2 = new frm_EmpAddAttLeave(employeeAttendance, false);
        frm2.setVisible(true);

    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Invalid date/time format: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid numeric format: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error processing attendance data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    // Helper method to safely parse doubles
    private double parseDoubleSafe(Object value) {
    try {
        return Double.parseDouble(value.toString());
    } catch (NumberFormatException e) {
        return 0.0;  // Default value if parsing fails
    }
          
    }//GEN-LAST:event_btn_LeaveRequestActionPerformed

    private void btn_EmpRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EmpRecordsActionPerformed
        new frm_EmployeesRecords().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btn_EmpRecordsActionPerformed

    private void btn_LeaveApprovalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LeaveApprovalActionPerformed
        new frm_EmpAttLeaveApproval().setVisible(true);
       
    }//GEN-LAST:event_btn_LeaveApprovalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_EmpAttLeave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_EmpAttLeave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_EmpAttLeave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_EmpAttLeave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_EmpAttLeave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_EmpRecords;
    private javax.swing.JButton btn_LeaveApproval;
    private javax.swing.JButton btn_LeaveMgt;
    private javax.swing.JButton btn_LeaveRequest;
    private javax.swing.JButton btn_Logout;
    private javax.swing.JButton btn_MyRecords;
    private javax.swing.JButton btn_PayrollSummary;
    private javax.swing.JButton btn_Profile;
    private javax.swing.JButton btn_SalaryAndStatutory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Attendance;
    private javax.swing.JTextField txt_slBal;
    private javax.swing.JTextField txt_slCount;
    private javax.swing.JTextField txt_slUsed;
    private javax.swing.JTextField txt_vlBal;
    private javax.swing.JTextField txt_vlCount;
    private javax.swing.JTextField txt_vlUsed;
    // End of variables declaration//GEN-END:variables
}
