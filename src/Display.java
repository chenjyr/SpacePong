/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Gui.java
 *
 * Created on Nov 14, 2010, 11:00:31 PM
 */

/**
 *
 * @author SpecC
 */
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Display extends javax.swing.JFrame {

	
	
	
    /** Creates new form Gui */
    public Display() {
        initComponents();
        Server.setVisible(false);
        Disconnect.setVisible(false);
        Connect.setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {

        Base = new javax.swing.JLayeredPane();
        Main = new javax.swing.JLayeredPane();
        Multi = new javax.swing.JButton();
        Local = new javax.swing.JButton();
        Single = new javax.swing.JButton();
        Internet = new javax.swing.JLayeredPane();
        IP = new javax.swing.JTextField();
        Port = new javax.swing.JTextField();
        IPLabel = new javax.swing.JLabel();
        PortLabel = new javax.swing.JLabel();
        clientButton = new javax.swing.JRadioButton();
        serverButton = new javax.swing.JRadioButton();
        Disconnect = new javax.swing.JButton();
        Connect = new javax.swing.JButton();
        Server = new javax.swing.JButton();
        Menu = new javax.swing.JButton();
        Display = new javax.swing.JLayeredPane();
        Display1 = new MainMenu();
        Display2 = new SinglePlayer();
        Display3 = new LocalMultiPlayer();
        Display4 = new MultiPlayer();
       
        Display1.setVisible(true);
        Display2.setVisible(false);
        Display3.setVisible(false);
        Display4.setVisible(false);
        
        Display1.init();
        


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Multi.setText("Internet Multiplayer ");
        Multi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MultiActionPerformed(evt);
            }
        });
        Multi.setBounds(10, 110, 130, 50);
        Main.add(Multi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Local.setText("Local Multiplayer");
        Local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalActionPerformed(evt);
            }
        });
        Local.setBounds(10, 60, 130, 50);
        Main.add(Local, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Single.setText("Single Player");
        Single.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SingleActionPerformed(evt);
            }
        });
        Single.setBounds(10, 10, 130, 50);
        Main.add(Single, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Main.setBounds(30, 0, 151, 168);
        Base.add(Main, javax.swing.JLayeredPane.DEFAULT_LAYER);

        IP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IPActionPerformed(evt);
            }
        });
        IP.setBounds(40, 40, 110, 20);
        Internet.add(IP, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PortActionPerformed(evt);
            }
        });
        Port.setBounds(40, 70, 110, 20);
        Internet.add(Port, javax.swing.JLayeredPane.DEFAULT_LAYER);

        IPLabel.setText("IP");
        IPLabel.setBounds(20, 40, 30, 14);
        Internet.add(IPLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        PortLabel.setText("Port");
        PortLabel.setBounds(10, 70, 30, 14);
        Internet.add(PortLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        clientButton.setText("Client");
        clientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientButtonActionPerformed(evt);
            }
        });
        clientButton.setBounds(10, 10, 60, 23);
        Internet.add(clientButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        serverButton.setText("Server");
        serverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverButtonActionPerformed(evt);
            }
        });
        serverButton.setBounds(70, 10, 70, 23);
        Internet.add(serverButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Disconnect.setText("Disconnect");
        Disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisconnectActionPerformed(evt);
            }
        });
        Disconnect.setBounds(50, 150, 100, 50);
        Internet.add(Disconnect, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Connect.setText("Connect");
        Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectActionPerformed(evt);
            }
        });      
        
        Connect.setBounds(50, 100, 100, 50);
        Internet.add(Connect, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Server.setText("Host"); // NOI18N
        Server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServerActionPerformed(evt);
            }
        });
        Server.setBounds(50, 100, 100, 50);
        Internet.add(Server, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Internet.setBounds(0, 0, 160, 230);
        Base.add(Internet, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Internet.setVisible(false);

        Menu.setText("Back to Menu");
        Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuActionPerformed(evt);
            }
        });
        Menu.setBounds(40, 550, 130, 60);
        Base.add(Menu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Display1.setBounds(0, 0, 400, 600);
        Display.add(Display1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Display2.setBounds(0, 0, 400, 600);
        Display.add(Display2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Display3.setBounds(0, 0, 400, 600);
        Display.add(Display3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Display4.setBounds(0, 0, 400, 600);
        Display.add(Display4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Display, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Base, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Base, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(Display, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void serverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverButtonActionPerformed
        clientButton.setSelected(false);
        IP.setEditable(false);
        Connect.setVisible(false);
        Server.setVisible(true);
        Disconnect.setVisible(true);

    }//GEN-LAST:event_serverButtonActionPerformed

    private void IPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IPActionPerformed

    private void PortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PortActionPerformed

    private void clientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientButtonActionPerformed

        serverButton.setSelected(false);
        IP.setEditable(true);
        Port.setEditable(true);
        Server.setVisible(false);
        Connect.setVisible(true);
        Disconnect.setVisible(true);


        // TODO add your handling code here:
    }//GEN-LAST:event_clientButtonActionPerformed

    private void MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuActionPerformed

            Main.setVisible(true);
            Internet.setVisible(false);
         
             
            Display1.init();
            Display2.stop();
            Display3.stop();
            Display4.stop();
            Display1.setVisible(true);
            Display2.setVisible(false);
            Display3.setVisible(false);
            Display4.setVisible(false);

            
            
            
       
            
    }//GEN-LAST:event_MenuActionPerformed

    private void MultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MultiActionPerformed

    	  Main.setVisible(false);
          Internet.setVisible(true);
    
          
          // default Server on 
    	/*
          serverButton.setSelected(true);
          IP.setEditable(false);
          Connect.setVisible(false);
          Server.setVisible(true);
          Disconnect.setVisible(true);
    	
          Display4.server = true;
         */ 
    	
    	
          
            
            Display4.init();
            Display1.setVisible(false);
            Display4.setVisible(true);
         
            

    }//GEN-LAST:event_MultiActionPerformed

    private void LocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalActionPerformed

            Main.setVisible(false);
            Internet.setVisible(false);
            
            Display3.init();
            Display1.setVisible(false);
            Display3.setVisible(true);
            
            
   
    }//GEN-LAST:event_LocalActionPerformed

    private void SingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SingleActionPerformed
        Main.setVisible(false);
        Internet.setVisible(false);
        
        Display2.init();
        Display1.setVisible(false);
        Display2.setVisible(true);



    }//GEN-LAST:event_SingleActionPerformed

    
    
    private boolean validateIPPort()
    {
    	int tmpInt;
       	String tmpStr = "";
    	StringTokenizer token;
    	
    	
    	if(clientButton.isSelected())
    	{
	    	tmpStr = IP.getText();
	    	
	    	if((tmpStr.equals("")))
	    		return false;
	    	
	    	// check for IP 
	       	token = new StringTokenizer(tmpStr,".");
	       	
	       	if(token.countTokens() != 4)
	       	return false;
	       	
		   	while(token.hasMoreTokens())
		   	{
		   		tmpInt = Integer.parseInt(token.nextToken());	   		
		   		if(tmpInt < 0 || tmpInt > 255)
		   		{
		   			return false;
		   		}
		   		
		   	}
    	}
	   	
	   	Display4.IP = IP.getText();
	       	
    	// check for Port 
     	tmpStr = Port.getText();
        
     	if((tmpStr.equals("")))
    		return false;
    	
     	tmpInt = Integer.parseInt(tmpStr);
     	if(tmpInt >= 0 && tmpInt <= 65535)
     	{
     		Display4.Port = tmpInt;
     	}
     	else
     	{
     		return false;
     	}
    	
    	return true;
    	
    	
    }
    
    
    private void ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServerActionPerformed

   
    	if(!validateIPPort())
    	{
    		 JOptionPane.showMessageDialog(Display,"Invalid IP/Port \n IP must be between 0 and 255 \n and in this format 255.255.255.255 \n Port must be in range between 0 and 65535","Error",0);
    	}
    	else
    	{
    		Display4.state = 3; 
    		Display4.server = false;
    		
    		
    	} 
        
        
    }//GEN-LAST:event_ServerActionPerformed

    
    
    private void ServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServerActionPerformed

    	if(!validateIPPort())
    	{
    		 JOptionPane.showMessageDialog(Display,"Port must be in range between 0 and 65535","Error",0);
    	}
    	else
    	{
    		Display4.state = 3;
    		Display4.server = true;
    	}
        
        
    }//GEN-LAST:event_ServerActionPerformed

    private void DisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisconnectActionPerformed

    	Display4.state = 1;
    	
    	
    }//GEN-LAST:event_DisconnectActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Display().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane Base;
    private javax.swing.JButton Connect;
    private javax.swing.JButton Disconnect;
    private javax.swing.JLayeredPane Display;
    private MainMenu Display1;
    private SinglePlayer Display2;
    private LocalMultiPlayer Display3;
    private MultiPlayer Display4;
    private javax.swing.JTextField IP;
    private javax.swing.JLabel IPLabel;
    private javax.swing.JLayeredPane Internet;
    private javax.swing.JButton Local;
    private javax.swing.JLayeredPane Main;
    private javax.swing.JButton Menu;
    private javax.swing.JButton Multi;
    private javax.swing.JTextField Port;
    private javax.swing.JLabel PortLabel;
    private javax.swing.JButton Server;
    private javax.swing.JButton Single;
    private javax.swing.JRadioButton clientButton;
    private javax.swing.JRadioButton serverButton;
    // End of variables declaration//GEN-END:variables

}