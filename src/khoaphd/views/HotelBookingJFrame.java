package khoaphd.views;

import java.awt.CardLayout;
import java.awt.Image;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import khoaphd.cart.CartItem;
import khoaphd.cart.CartObject;
import khoaphd.dtos.HotelDTO;
import khoaphd.dtos.HotelRoomDTO;
import khoaphd.dtos.UserDTO;
import khoaphd.models.BookingDetailDAO;
import khoaphd.models.HotelDAO;
import khoaphd.models.HotelRoomDAO;
import khoaphd.models.RoomTypeDAO;
import khoaphd.utils.DateConversion;
import khoaphd.utils.ScaledImage;

/**
 *
 * @author KhoaPHD
 */
public class HotelBookingJFrame extends javax.swing.JFrame {

    private BookingDetailDAO bookingDetailDAO;
    private HotelDAO hotelDAO;
    private HotelRoomDAO hotelRoomDAO;
    private RoomTypeDAO roomTypeDAO;
    
    private DefaultTableModel hotelsModel;
    private DefaultTableModel roomsModel;
    
    UserDTO userDTO;
    
    public HotelBookingJFrame() {
        initComponents();
        
        bookingDetailDAO = new BookingDetailDAO();
        hotelDAO = new HotelDAO();
        hotelRoomDAO = new HotelRoomDAO();
        roomTypeDAO = new RoomTypeDAO();
        
        hotelsModel = (DefaultTableModel)tblHotels.getModel();
        roomsModel = (DefaultTableModel)tblRooms.getModel();
        
        try {
            loadLogo();
            loadAllHotels();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            if (!e.getMessage().contains("read input file")) {
                e.printStackTrace();
            }
        }
    }
    
    public void setLoginUser(UserDTO userDTO) {
        this.userDTO = userDTO;
        if (this.userDTO != null) {
            CardLayout cardLayout = (CardLayout)pnlMain.getLayout();
            cardLayout.show(pnlMain, "pnlUser");
            lbWelcome.setText("Welcome " + this.userDTO.getUserName());
        }
    }
    
    public void loadLogo() throws IOException {
        String filepath = "img/logo.png";
        int width = pnlIcon.getWidth();
        int height = pnlIcon.getHeight();
        try {
            Image logo = ScaledImage.getScaledImage(filepath, width, height);
            lbIcon.setIcon(new ImageIcon(logo));
        } finally {
            lbIconTitle.setText("<html>Hotel<br/>Booking</html>");
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
    
    public void loadAllHotels() throws SQLException, ClassNotFoundException {
        hotelsModel.setRowCount(0);
        List<HotelDTO> list = hotelDAO.getAllHotels();
        for (HotelDTO dto : list) {
            hotelsModel.addRow(dto.toVector());
        }
    }
    
    public void loadHotelsToSearch(List<HotelDTO> list) {
        hotelsModel.setRowCount(0);
        for (HotelDTO dto : list) {
            hotelsModel.addRow(dto.toVector());
        }
    }
    
    public void loadRooms(List<Vector> list) {
        roomsModel.setRowCount(0);
        for (Vector v : list) {
            roomsModel.addRow(v);
        }
    }
    
    public boolean validateInput() {
        String checkinStr = txtCheckinDate.getText().trim();
        if (checkinStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Checkin date is required");
            txtCheckinDate.requestFocus();
            return false;
        } else if (!checkinStr.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            JOptionPane.showMessageDialog(this, "Checkin date must follow format YYYY-MM-DD");
            txtCheckinDate.requestFocus();
            return false;
        }
        Date checkinDate = null;
        try {
            checkinDate = DateConversion.convertStringToDate(checkinStr, "yyyy-MM-dd");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid checkin date");
            txtCheckinDate.requestFocus();
            return false;
        }
        Date currentDate = new Date(System.currentTimeMillis());
        if (checkinDate.compareTo(currentDate) <= 0) {
            JOptionPane.showMessageDialog(this, "Checkin date must be after current date");
            txtCheckinDate.requestFocus();
            return false;
        }
        
        String checkoutStr = txtCheckoutDate.getText().trim();
        if (checkoutStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Checkout date is required");
            txtCheckoutDate.requestFocus();
            return false;
        } else if (!checkoutStr.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            JOptionPane.showMessageDialog(this, "Checkout date must follow format YYYY-MM-DD");
            txtCheckoutDate.requestFocus();
            return false;
        }
        Date checkoutDate = null;
        try {
            checkoutDate = DateConversion.convertStringToDate(checkoutStr, "yyyy-MM-dd");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid checkout date");
            txtCheckoutDate.requestFocus();
            return false;
        }
        if (checkoutDate.compareTo(checkinDate) <= 0) {
            JOptionPane.showMessageDialog(this, "Checkout date must be after checkin date");
            txtCheckoutDate.requestFocus();
            return false;
        }
        
        if (!txtRoomAmount.getText().trim().isEmpty()) {
            try {
                int amount = Integer.parseInt(txtRoomAmount.getText().trim());
                if (amount < 0) {
                    JOptionPane.showMessageDialog(this, "Room amount cannot be negative");
                    txtRoomAmount.requestFocus();
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Room amount must be an integer");
                txtRoomAmount.requestFocus();
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlIcon = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        lbIconTitle = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        pnlGuest = new javax.swing.JPanel();
        btnLogIn = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        pnlUser = new javax.swing.JPanel();
        lbWelcome = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHotels = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRooms = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHotelName = new javax.swing.JTextField();
        txtArea = new javax.swing.JTextField();
        txtCheckinDate = new javax.swing.JTextField();
        txtCheckoutDate = new javax.swing.JTextField();
        txtRoomAmount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbHotelID = new javax.swing.JLabel();
        lbRoomTypeID = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        lbCheckinDate = new javax.swing.JLabel();
        lbCheckoutDate = new javax.swing.JLabel();
        btnAddToCart = new javax.swing.JButton();
        btnViewCart = new javax.swing.JButton();
        pnlHotelImage = new javax.swing.JPanel();
        lbHotelImage = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel Booking");

        javax.swing.GroupLayout pnlIconLayout = new javax.swing.GroupLayout(pnlIcon);
        pnlIcon.setLayout(pnlIconLayout);
        pnlIconLayout.setHorizontalGroup(
            pnlIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );
        pnlIconLayout.setVerticalGroup(
            pnlIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lbIconTitle.setFont(new java.awt.Font("AlternateGothic2 BT", 0, 24)); // NOI18N
        lbIconTitle.setText(" ");

        pnlMain.setLayout(new java.awt.CardLayout());

        btnLogIn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogIn.setText("Log in");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });

        btnSignUp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSignUp.setText("Sign up");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGuestLayout = new javax.swing.GroupLayout(pnlGuest);
        pnlGuest.setLayout(pnlGuestLayout);
        pnlGuestLayout.setHorizontalGroup(
            pnlGuestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGuestLayout.createSequentialGroup()
                .addContainerGap(220, Short.MAX_VALUE)
                .addComponent(btnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        pnlGuestLayout.setVerticalGroup(
            pnlGuestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGuestLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlGuestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogIn)
                    .addComponent(btnSignUp))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pnlMain.add(pnlGuest, "pnlGuest");

        lbWelcome.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbWelcome.setForeground(java.awt.Color.red);
        lbWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        btnLogOut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogOut.setText("Log out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUserLayout = new javax.swing.GroupLayout(pnlUser);
        pnlUser.setLayout(pnlUserLayout);
        pnlUserLayout.setHorizontalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        pnlUserLayout.setVerticalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbWelcome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogOut, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(25, 25, 25))
        );

        pnlMain.add(pnlUser, "pnlUser");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("Hotels");

        tblHotels.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblHotels.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hotel ID", "Hotel Name", "Area"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHotels.setRowHeight(20);
        tblHotels.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblHotels.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHotelsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHotels);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Rooms in Hotel");

        tblRooms.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room type ID", "Room type", "Price", "Available"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRooms.setRowHeight(20);
        tblRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRoomsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRooms);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Find available rooms", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), java.awt.Color.blue)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Hotel Name:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Area:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Checkin Date:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Checkout Date:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Room Amount:");

        txtHotelName.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtArea.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtCheckinDate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtCheckoutDate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtRoomAmount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("(YYYY-MM-DD)");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("(YYYY-MM-DD)");

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSearch.setForeground(java.awt.Color.red);
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtCheckoutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtHotelName)
                                .addComponent(txtArea, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(txtCheckinDate))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(24, 24, 24)
                        .addComponent(txtRoomAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHotelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCheckinDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCheckoutDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtRoomAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Hotel ID:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Room type ID:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel14.setText("Price:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setText("Quantity:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setText("Checkin Date:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel17.setText("Checkout Date:");

        lbHotelID.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        lbRoomTypeID.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        lbPrice.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        txtQuantity.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        lbCheckinDate.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        lbCheckoutDate.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        btnAddToCart.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAddToCart.setForeground(java.awt.Color.red);
        btnAddToCart.setText("Add to Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        btnViewCart.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnViewCart.setForeground(java.awt.Color.red);
        btnViewCart.setText("View Cart");
        btnViewCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbCheckinDate))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbCheckoutDate)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(lbHotelID))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(lbRoomTypeID))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(lbPrice))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnViewCart, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbHotelID))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lbRoomTypeID))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lbPrice))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lbCheckinDate))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lbCheckoutDate))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddToCart)
                    .addComponent(btnViewCart))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHotelImageLayout = new javax.swing.GroupLayout(pnlHotelImage);
        pnlHotelImage.setLayout(pnlHotelImageLayout);
        pnlHotelImageLayout.setHorizontalGroup(
            pnlHotelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHotelImage, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );
        pnlHotelImageLayout.setVerticalGroup(
            pnlHotelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHotelImage, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );

        btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbIconTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(pnlHotelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbIconTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(btnRefresh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlHotelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        new LoginJFrame(this).setVisible(true);
    }//GEN-LAST:event_btnLogInActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(this, "Logging out will discard current cart. Proceed?");
        if (confirmation == JOptionPane.YES_OPTION) {
            userDTO.setCart(null);
            userDTO = null;
            CardLayout cardLayout = (CardLayout)pnlMain.getLayout();
            cardLayout.show(pnlMain, "pnlGuest");
            btnRefreshActionPerformed(null);
        }
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        new SignUpJFrame().setVisible(true);
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void tblHotelsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHotelsMouseClicked
        int row = tblHotels.getSelectedRow();
        if (row != -1) {
            lbRoomTypeID.setText("");
            lbPrice.setText("");
            txtQuantity.setText("");
            txtHotelName.setText((String)tblHotels.getValueAt(row, 1));
            txtArea.setText((String)tblHotels.getValueAt(row, 2));
            
            String hotelID = (String)tblHotels.getValueAt(row, 0);
            try {
                loadHotelImage(hotelID);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                if (!e.getMessage().contains("read input file")) {
                    e.printStackTrace();
                }
            }
            lbHotelID.setText(hotelID);
            
            if (!lbCheckinDate.getText().trim().isEmpty() && !lbCheckoutDate.getText().trim().isEmpty()) {
                Date checkinDate = null, checkoutDate = null;
                Map<String, Integer> map = null;
                List<HotelRoomDTO> allList = null;
                List<Vector> displayList = new ArrayList<>();
                try {
                    checkinDate = DateConversion.convertStringToDate(lbCheckinDate.getText().trim(), "yyyy-MM-dd");
                    checkoutDate = DateConversion.convertStringToDate(lbCheckoutDate.getText().trim(), "yyyy-MM-dd");
                    map = bookingDetailDAO.getBookedRoomTypes(hotelID, checkinDate, checkoutDate);
                    allList = hotelRoomDAO.getEachRoomTypeAmount(hotelID);
                    for (HotelRoomDTO dto : allList) {
                        if (map.get(dto.getRoomTypeID()) != null) {
                            Integer booked = map.get(dto.getRoomTypeID());
                            Integer max = dto.getMaxQuantity();
                            if (max > booked) {
                                String roomType = roomTypeDAO.getRoomType(dto.getRoomTypeID());
                                dto.setMaxQuantity(max - booked);
                                
                                Vector v = new Vector();
                                v.add(dto.getRoomTypeID());
                                v.add(roomType);
                                v.add(dto.getPrice());
                                v.add(dto.getMaxQuantity());
                                displayList.add(v);
                            } else {
                                allList.remove(dto);
                            }
                        } else {
                            String roomType = roomTypeDAO.getRoomType(dto.getRoomTypeID());
                            Vector v = new Vector();
                            v.add(dto.getRoomTypeID());
                            v.add(roomType);
                            v.add(dto.getPrice());
                            v.add(dto.getMaxQuantity());
                            displayList.add(v);
                        }
                    }
                    loadRooms(displayList);
                } catch (ClassNotFoundException | SQLException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_tblHotelsMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        roomsModel.setRowCount(0);
        lbHotelImage.setIcon(null);
        lbHotelID.setText("");
        lbRoomTypeID.setText("");
        lbPrice.setText("");
        txtQuantity.setText("");
        lbCheckinDate.setText("");
        lbCheckoutDate.setText("");
        
        try {
            loadAllHotels();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if (!validateInput()) {
            return;
        }
        
        roomsModel.setRowCount(0);
        lbHotelImage.setIcon(null);
        lbHotelID.setText("");
        
        String hotelName = txtHotelName.getText().trim();
        String area = txtArea.getText().trim();
        Date checkinDate = null, checkoutDate = null;
        try {
            checkinDate = DateConversion.convertStringToDate(txtCheckinDate.getText().trim(), "yyyy-MM-dd");
            checkoutDate = DateConversion.convertStringToDate(txtCheckoutDate.getText().trim(), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        List<HotelRoomDTO> allList = null;
        Map<String, Integer> map = null;
        List<HotelDTO> displayList = new ArrayList<>();
        try {
            allList = hotelRoomDAO.getRoomInAllHotels();
            map = bookingDetailDAO.getBookedRooms(hotelName, area, checkinDate, checkoutDate);
            for (HotelRoomDTO dto : allList) {
                if (map.get(dto.getHotelID()) != null) {
                    Integer booked = map.get(dto.getHotelID());
                    Integer max = dto.getMaxQuantity();
                    if (max > booked) {
                        HotelDTO displayDTO = hotelDAO.getHotelByID(dto.getHotelID());
                        displayList.add(displayDTO);
                    }
                } else {
                    HotelDTO displayDTO = hotelDAO.getHotelByID(dto.getHotelID());
                    displayList.add(displayDTO);
                }
            }
            loadHotelsToSearch(displayList);
            lbCheckinDate.setText(txtCheckinDate.getText().trim());
            lbCheckoutDate.setText(txtCheckoutDate.getText().trim());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRoomsMouseClicked
        int row = tblRooms.getSelectedRow();
        if (row != -1) {
            lbRoomTypeID.setText((String)tblRooms.getValueAt(row, 0));
            String price = ((Double)tblRooms.getValueAt(row, 2)).toString();
            lbPrice.setText(price);
        }
    }//GEN-LAST:event_tblRoomsMouseClicked

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        if (userDTO == null) {
            JOptionPane.showMessageDialog(this, "Please log in to use this function");
            return;
        }
        
        if (userDTO.getRole().equalsIgnoreCase("admin")) {
            JOptionPane.showMessageDialog(this, "Admin cannot use this function");
            return;
        }
        
        if (lbRoomTypeID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose an available room to add");
            return;
        }
        
        int confirmation = JOptionPane.showConfirmDialog(this, "Add this room to cart?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }
        
        int quantity;
        if (txtQuantity.getText().trim().isEmpty()) {
            quantity = 1;
        } else {
            try {
                quantity = Integer.parseInt(txtQuantity.getText().trim());
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be positive");
                    txtQuantity.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Quantity is an integer");
                txtQuantity.requestFocus();
                return;
            }
        }
        
        String hotelID = lbHotelID.getText().trim();
        String roomTypeID = lbRoomTypeID.getText().trim();
        double price = Double.parseDouble(lbPrice.getText().trim());
        Date checkin = null, checkout = null;
        try {
            checkin = DateConversion.convertStringToDate(lbCheckinDate.getText().trim(), "yyyy-MM-dd");
            checkout = DateConversion.convertStringToDate(lbCheckoutDate.getText().trim(), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CartItem item = new CartItem(hotelID, roomTypeID, price, quantity, checkin, checkout);
        boolean isExisted = false;
        if (userDTO.getCart() == null) {
            userDTO.setCart(new CartObject());
        } else {
            for (CartItem inCartItem : userDTO.getCart().getItems()) {
                if (item.getHotelID().equalsIgnoreCase(inCartItem.getHotelID())) {
                    if (item.getRoomTypeID().equalsIgnoreCase(inCartItem.getRoomTypeID())) {
                        if (item.getCheckin().equals(inCartItem.getCheckin())) {
                            if (item.getCheckout().equals(inCartItem.getCheckout())) {
                                isExisted = true;
                                inCartItem.setQuantity(inCartItem.getQuantity() + item.getQuantity());
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (!isExisted) {
            userDTO.getCart().getItems().add(item);
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnViewCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCartActionPerformed
        if (userDTO == null) {
            JOptionPane.showMessageDialog(this, "Please log in to use this function");
            return;
        }
        
        if (userDTO.getRole().equalsIgnoreCase("admin")) {
            JOptionPane.showMessageDialog(this, "Admin cannot use this function");
        } else {
            new CartJFrame(this).setVisible(true);
        }
    }//GEN-LAST:event_btnViewCartActionPerformed

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
            java.util.logging.Logger.getLogger(HotelBookingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HotelBookingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HotelBookingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HotelBookingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HotelBookingJFrame frame = new HotelBookingJFrame();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JButton btnViewCart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCheckinDate;
    private javax.swing.JLabel lbCheckoutDate;
    private javax.swing.JLabel lbHotelID;
    private javax.swing.JLabel lbHotelImage;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbIconTitle;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbRoomTypeID;
    private javax.swing.JLabel lbWelcome;
    private javax.swing.JPanel pnlGuest;
    private javax.swing.JPanel pnlHotelImage;
    private javax.swing.JPanel pnlIcon;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlUser;
    private javax.swing.JTable tblHotels;
    private javax.swing.JTable tblRooms;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCheckinDate;
    private javax.swing.JTextField txtCheckoutDate;
    private javax.swing.JTextField txtHotelName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtRoomAmount;
    // End of variables declaration//GEN-END:variables
}