
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MadeRaditya
 */
public class draft1 extends javax.swing.JFrame {
    private final String fileName = "test.txt";
    private BufferedWriter bw;


    /**
     * Creates new form draft1
     */
    public draft1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        commandBox = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        post2 = new javax.swing.JTextField();
        post3 = new javax.swing.JTextField();
        post1 = new javax.swing.JTextField();
        post4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        post5 = new javax.swing.JTextField();
        post6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Bauhaus 93", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setText("iDo++");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        commandBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commandBoxActionPerformed(evt);
            }
        });
        getContentPane().add(commandBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 23, 315, 29));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 43, 197, 185));

        jLabel1.setText("Missing this?");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 23, -1, -1));

        jLabel3.setText("General Task");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 234, -1, -1));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 254, 197, 250));

        post2.setBackground(new java.awt.Color(51, 204, 0));
        getContentPane().add(post2, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 95, 212, 119));

        post3.setBackground(new java.awt.Color(51, 102, 255));
        post3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                post3ActionPerformed(evt);
            }
        });
        getContentPane().add(post3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 212, 119));

        post1.setBackground(new java.awt.Color(255, 102, 102));
        post1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                post1ActionPerformed(evt);
            }
        });
        getContentPane().add(post1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 95, 213, 119));

        post4.setBackground(new java.awt.Color(255, 255, 153));
        getContentPane().add(post4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 212, 119));

        jLabel4.setText("Today");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel5.setText("02/09/14");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, -1, -1));

        jLabel6.setText("03/09/14");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel7.setText("04/09/14");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, -1));

        post5.setBackground(new java.awt.Color(0, 255, 51));
        getContentPane().add(post5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 212, 119));

        post6.setBackground(new java.awt.Color(255, 102, 102));
        getContentPane().add(post6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 212, 119));

        jLabel8.setText("05/09/14");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        jLabel9.setText("06/09/14");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void commandBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commandBoxActionPerformed
        
        
        String input = commandBox.getText();
        String arrString[] = input.split(" ",2);
        String command = arrString[0];
        String theRest = arrString[1];
        
        switch (command) {
            case "start":
                try {
                    // TODO add your handling code here:
                    displayPost1();
                } catch (IOException ex) {
                    Logger.getLogger(draft1.class.getName()).log(Level.SEVERE, null, ex);
                }       break;
            case "edit":
                String arrString2[] = theRest.split(" ",3);
                String date = arrString2[0];
                String number = arrString2[1];
                String modification = arrString2[2];
                try {
                    editTask(date,number,modification);
                } catch (IOException ex) {
                    Logger.getLogger(draft1.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            
            
        }
    }//GEN-LAST:event_commandBoxActionPerformed

    private void post1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_post1ActionPerformed
        // TODO add your handling code here:
        /*FileReader fr = null;
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(draft1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            post1.read(fr,"test.txt");
        } catch (IOException ex) {
            Logger.getLogger(draft1.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
    }//GEN-LAST:event_post1ActionPerformed

    private void post3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_post3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_post3ActionPerformed

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
            java.util.logging.Logger.getLogger(draft1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(draft1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(draft1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(draft1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new draft1().setVisible(true);
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField commandBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField post1;
    private javax.swing.JTextField post2;
    private javax.swing.JTextField post3;
    private javax.swing.JTextField post4;
    private javax.swing.JTextField post5;
    private javax.swing.JTextField post6;
    // End of variables declaration//GEN-END:variables


    private void editTask(String date, String number, String modification) throws IOException {
        ArrayList<String> currDateTask;
        currDateTask = new ArrayList<>();
        //fileName=setFileName(date);
        //read the content of the file, put in the list
       BufferedReader br = null;
		try {
			String curr;
			br = new BufferedReader(new FileReader(fileName));
			while ((curr = br.readLine()) != null) {
				currDateTask.add(curr);
			}
		} catch (IOException ee) {
			ee.printStackTrace();
		}
                //delete the specific task in the date
                int position = Integer.parseInt(number);
                String toBeRemoved = currDateTask.get(position-1);
                currDateTask.remove(toBeRemoved);
                
                //insert the modification into the arrayList
                currDateTask.add(position-1, modification);
                
                //clear the file
                clearFile(fileName);
                
                //write to the file
                writeToFile(currDateTask,fileName);
                
                //then display in the UI
                displayPost1();
                
        
    }

    private void writeToFile(ArrayList<String> currDateTask, String fileName) throws IOException {
        try {
            bw = new BufferedWriter(new FileWriter(new File("C:/Users/MadeRaditya/Documents/NetBeansProjects/GUI draft 1/test.txt"), true));
            for (int i = 0; i < currDateTask.size(); i++) {
            
                bw.write(currDateTask.get(i) + "\n");
            }

		} catch (IOException ee) {
			ee.printStackTrace();
		} finally {
			//close the writer so that it can write to the file
            bw.close(); 
        }
        System.out.println("DONE!");
    }

    private void clearFile(String fileName) {
        try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("");
			bw.close();
		} catch (IOException ee) {
			ee.printStackTrace();
		}
    }

    private void displayPost1() throws FileNotFoundException, IOException {
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        post1.read(reader, this);
    }

    
    
}