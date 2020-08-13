package khoaphd.views;

import java.awt.Image;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import khoaphd.cart.CartItem;
import khoaphd.dtos.BookingDTO;
import khoaphd.dtos.HotelDTO;
import khoaphd.dtos.UserDTO;
import khoaphd.models.BookingDAO;
import khoaphd.models.HotelDAO;
import khoaphd.models.RoomTypeDAO;
import khoaphd.utils.DateConversion;
import khoaphd.utils.ScaledImage;

/**
 *
 * @author KhoaPHD
 */
public class CartJFrame extends javax.swing.JFrame {

    private BookingDAO bookingDAO;
    private HotelDAO hotelDAO;
    private RoomTypeDAO roomTypeDAO;
    
    private DefaultTableModel cartModel;
    
    private UserDTO userDTO;
    
    public CartJFrame() {
        initComponents();
    }
    
    public CartJFrame(HotelBookingJFrame frame) {
        initComponents();
        bookingDAO = new BookingDAO();
        hotelDAO = new HotelDAO();
        roomTypeDAO = new RoomTypeDAO();
        cartModel = (DefaultTableModel)tblCart.getModel();
        this.userDTO = frame.userDTO;
        setLocationRelativeTo(null);
        loadCart();
    }
    
    public void loadCart() {
        cartModel.setRowCount(0);
        if (userDTO.getCart() != null) {
            List<CartItem> list = userDTO.getCart().getItems();
            for (CartItem item : list) {
                cartModel.addRow(item.toVector());
            }
        }
    }
    
    public void loadHotelImage(String hotelID)
            throws SQLException, ClassNotFoundException, IOException {
        String filepath = hotelDAO.getHotelImagePath(hotelID);
        if (!filepath.equals("")) {
            int width = pnlHotelImage.getWidth();
            int height = pnlHotelImage.getHeight();
            Image hotelImg = ScaledImage.getScaledImage(filepath, width, height);
            lbHotelImage.setIcon(new ImageIcon(hotelImg));
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

        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pnlHotelImage = new javax.swing.JPanel();
        lbHotelImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbHotelID = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbHotelName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbArea = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbRoomTypeID = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbRoomType = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lbCheckinDate = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbCheckoutDate = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cart");

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tblCart.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hotel ID", "Room type ID", "Price", "Quantity", "Checkin Date", "Checkout Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCartMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCart);

        btnRemove.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnRemove.setText("Remove");

        btnConfirm.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnReload.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnReload.setText("Reload");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlHotelImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlHotelImageLayout = new javax.swing.GroupLayout(pnlHotelImage);
        pnlHotelImage.setLayout(pnlHotelImageLayout);
        pnlHotelImageLayout.setHorizontalGroup(
            pnlHotelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHotelImage, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        pnlHotelImageLayout.setVerticalGroup(
            pnlHotelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHotelImage, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Hotel ID:");

        lbHotelID.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Hotel Name:");

        lbHotelName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Area:");

        lbArea.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Room type ID:");

        lbRoomTypeID.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Room type:");

        lbRoomType.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Price:");

        lbPrice.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Quantity:");

        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Checkin Date:");

        lbCheckinDate.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Checkout Date:");

        lbCheckoutDate.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton1.setText("Update");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHotelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbHotelID)
                        .addGap(211, 211, 211)
                        .addComponent(lbRoomTypeID))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbHotelName))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbRoomType))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(120, 120, 120)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel9)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lbCheckoutDate))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lbCheckinDate))))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPrice))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(98, 98, 98)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbArea)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lbHotelID)
                            .addComponent(jLabel5)
                            .addComponent(lbRoomTypeID)
                            .addComponent(jLabel1)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbHotelName)
                            .addComponent(jLabel6)
                            .addComponent(lbRoomType)
                            .addComponent(jLabel8)
                            .addComponent(lbCheckinDate))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lbArea)
                            .addComponent(jLabel7)
                            .addComponent(lbPrice)
                            .addComponent(jLabel9)
                            .addComponent(lbCheckoutDate)))
                    .addComponent(pnlHotelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove)
                    .addComponent(btnConfirm)
                    .addComponent(btnReload))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        lbHotelImage.setIcon(null);
        lbHotelID.setText("");
        lbHotelName.setText("");
        lbArea.setText("");
        lbRoomTypeID.setText("");
        lbRoomType.setText("");
        lbPrice.setText("");
        txtQuantity.setText("");
        lbCheckinDate.setText("");
        lbCheckoutDate.setText("");
        loadCart();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void tblCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCartMouseClicked
        int row = tblCart.getSelectedRow();
        if (row != -1) {
            String hotelID = (String)tblCart.getValueAt(row, 0);
            try {
                loadHotelImage(hotelID);
                HotelDTO hotelDTO = hotelDAO.getHotelByID(hotelID);
                String roomTypeID = (String)tblCart.getValueAt(row, 1);
                String roomType = roomTypeDAO.getRoomType(roomTypeID);
                String price = ((Double)tblCart.getValueAt(row, 2)).toString();
                String quantity = ((Integer)tblCart.getValueAt(row, 3)).toString();
                String checkin = DateConversion.convertDateToString((Date)tblCart.getValueAt(row, 4), "yyyy-MM-dd");
                String checkout = DateConversion.convertDateToString((Date)tblCart.getValueAt(row, 5), "yyyy-MM-dd");
                
                lbHotelID.setText(hotelID);
                lbHotelName.setText(hotelDTO.getHotelName());
                lbArea.setText(hotelDTO.getArea());
                lbRoomTypeID.setText(roomTypeID);
                lbRoomType.setText(roomType);
                lbPrice.setText(price);
                txtQuantity.setText(quantity);
                lbCheckinDate.setText(checkin);
                lbCheckoutDate.setText(checkout);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                if (!e.getMessage().contains("read input file")) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_tblCartMouseClicked

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        if (userDTO.getCart() == null) {
            JOptionPane.showMessageDialog(this, "No cart is existed");
            return;
        }
        if (userDTO.getCart().getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No room in cart to confirm");
            return;
        }
        
        int confirmation = JOptionPane.showConfirmDialog(this, "Confirm this cart?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }
        
        String userID = userDTO.getUserID();
            Date bookingDate = new Date(System.currentTimeMillis());
            BookingDTO dto = new BookingDTO(userID, bookingDate);
            try {
                bookingDAO.insertNewBooking(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }//GEN-LAST:event_btnConfirmActionPerformed

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
            java.util.logging.Logger.getLogger(CartJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CartJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CartJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CartJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CartJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbArea;
    private javax.swing.JLabel lbCheckinDate;
    private javax.swing.JLabel lbCheckoutDate;
    private javax.swing.JLabel lbHotelID;
    private javax.swing.JLabel lbHotelImage;
    private javax.swing.JLabel lbHotelName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbRoomType;
    private javax.swing.JLabel lbRoomTypeID;
    private javax.swing.JPanel pnlHotelImage;
    private javax.swing.JTable tblCart;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}