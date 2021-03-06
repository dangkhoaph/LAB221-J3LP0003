USE [master]
GO
/****** Object:  Database [HotelManagement]    Script Date: 8/14/2020 12:19:24 AM ******/
CREATE DATABASE [HotelManagement]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HotelManagement', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\HotelManagement.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'HotelManagement_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\HotelManagement_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [HotelManagement] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HotelManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HotelManagement] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HotelManagement] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HotelManagement] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HotelManagement] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HotelManagement] SET ARITHABORT OFF 
GO
ALTER DATABASE [HotelManagement] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HotelManagement] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HotelManagement] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HotelManagement] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HotelManagement] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HotelManagement] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HotelManagement] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HotelManagement] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HotelManagement] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HotelManagement] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HotelManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HotelManagement] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HotelManagement] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HotelManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HotelManagement] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HotelManagement] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HotelManagement] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HotelManagement] SET RECOVERY FULL 
GO
ALTER DATABASE [HotelManagement] SET  MULTI_USER 
GO
ALTER DATABASE [HotelManagement] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HotelManagement] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HotelManagement] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HotelManagement] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [HotelManagement] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'HotelManagement', N'ON'
GO
USE [HotelManagement]
GO
/****** Object:  Table [dbo].[Booking]    Script Date: 8/14/2020 12:19:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Booking](
	[BookingID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [varchar](50) NOT NULL,
	[DiscountCode] [varchar](10) NULL,
	[BookingDate] [date] NULL,
 CONSTRAINT [PK_Booking] PRIMARY KEY CLUSTERED 
(
	[BookingID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BookingDetail]    Script Date: 8/14/2020 12:19:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BookingDetail](
	[BookingDetailID] [int] NOT NULL,
	[BookingID] [int] NOT NULL,
	[HotelID] [varchar](7) NOT NULL,
	[RoomTypeID] [varchar](5) NOT NULL,
	[Price] [float] NULL,
	[Quantity] [int] NULL,
	[CheckinDate] [date] NULL,
	[CheckoutDate] [date] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_BookingDetail] PRIMARY KEY CLUSTERED 
(
	[BookingDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 8/14/2020 12:19:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Discount](
	[Code] [varchar](10) NOT NULL,
	[Value] [float] NULL,
	[ExpiryDate] [date] NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[Code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Hotel]    Script Date: 8/14/2020 12:19:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Hotel](
	[HotelID] [varchar](7) NOT NULL,
	[HotelName] [varchar](50) NULL,
	[Area] [varchar](50) NULL,
	[Image] [varchar](50) NULL,
 CONSTRAINT [PK_Hotel] PRIMARY KEY CLUSTERED 
(
	[HotelID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HotelRoom]    Script Date: 8/14/2020 12:19:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HotelRoom](
	[HotelID] [varchar](7) NOT NULL,
	[RoomTypeID] [varchar](5) NOT NULL,
	[Price] [float] NULL,
	[MaxQuantity] [int] NULL,
 CONSTRAINT [PK_HotelRoom] PRIMARY KEY CLUSTERED 
(
	[HotelID] ASC,
	[RoomTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RoomType]    Script Date: 8/14/2020 12:19:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RoomType](
	[RoomTypeID] [varchar](5) NOT NULL,
	[RoomType] [varchar](50) NULL,
 CONSTRAINT [PK_RoomType] PRIMARY KEY CLUSTERED 
(
	[RoomTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[User]    Script Date: 8/14/2020 12:19:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[User](
	[UserID] [varchar](50) NOT NULL,
	[Password] [varchar](50) NULL,
	[UserName] [varchar](50) NULL,
	[Phone] [varchar](10) NULL,
	[Address] [varchar](50) NULL,
	[Role] [varchar](10) NULL,
	[Status] [bit] NULL,
	[CreateDate] [date] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Booking] ON 

INSERT [dbo].[Booking] ([BookingID], [UserID], [DiscountCode], [BookingDate]) VALUES (1, N'khoaphd@gmail.com', NULL, CAST(N'2020-03-01' AS Date))
INSERT [dbo].[Booking] ([BookingID], [UserID], [DiscountCode], [BookingDate]) VALUES (2, N'nhunnq@gmail.com', NULL, CAST(N'2020-03-01' AS Date))
INSERT [dbo].[Booking] ([BookingID], [UserID], [DiscountCode], [BookingDate]) VALUES (3, N'hienht@gmail.com', NULL, CAST(N'2020-03-01' AS Date))
INSERT [dbo].[Booking] ([BookingID], [UserID], [DiscountCode], [BookingDate]) VALUES (4, N'trangcq@gmail.com', NULL, CAST(N'2020-03-01' AS Date))
INSERT [dbo].[Booking] ([BookingID], [UserID], [DiscountCode], [BookingDate]) VALUES (1005, N'testuser@gmail.com', NULL, CAST(N'2020-03-26' AS Date))
SET IDENTITY_INSERT [dbo].[Booking] OFF
INSERT [dbo].[BookingDetail] ([BookingDetailID], [BookingID], [HotelID], [RoomTypeID], [Price], [Quantity], [CheckinDate], [CheckoutDate], [Status]) VALUES (1, 1, N'HT00001', N'RT001', 50, 2, CAST(N'2020-04-20' AS Date), CAST(N'2020-04-25' AS Date), 1)
INSERT [dbo].[BookingDetail] ([BookingDetailID], [BookingID], [HotelID], [RoomTypeID], [Price], [Quantity], [CheckinDate], [CheckoutDate], [Status]) VALUES (2, 1, N'HT00001', N'RT003', 250, 1, CAST(N'2020-04-20' AS Date), CAST(N'2020-04-25' AS Date), 1)
INSERT [dbo].[BookingDetail] ([BookingDetailID], [BookingID], [HotelID], [RoomTypeID], [Price], [Quantity], [CheckinDate], [CheckoutDate], [Status]) VALUES (3, 2, N'HT00001', N'RT001', 50, 3, CAST(N'2020-04-19' AS Date), CAST(N'2020-04-25' AS Date), 1)
INSERT [dbo].[BookingDetail] ([BookingDetailID], [BookingID], [HotelID], [RoomTypeID], [Price], [Quantity], [CheckinDate], [CheckoutDate], [Status]) VALUES (4, 3, N'HT00001', N'RT003', 50, 2, CAST(N'2020-04-22' AS Date), CAST(N'2020-04-28' AS Date), 1)
INSERT [dbo].[BookingDetail] ([BookingDetailID], [BookingID], [HotelID], [RoomTypeID], [Price], [Quantity], [CheckinDate], [CheckoutDate], [Status]) VALUES (5, 4, N'HT00004', N'RT001', 80, 5, CAST(N'2020-04-25' AS Date), CAST(N'2020-04-30' AS Date), 1)
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00001', N'Aurora', N'Da Lat', N'img/dalat_aurorahotel.jpg')
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00002', N'Vy Anh', N'Da Lat', N'img/dalat_vyanhhotel.jpg')
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00003', N'Dragon Palace II', N'Ho Chi Minh', N'img/hcm_dragonpalace2hotel.jpg')
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00004', N'Mega Light', N'Nha Trang', N'img/nhatrang_megalighthotel.jpg')
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00005', N'Hoai Anh', N'Vung Tau', N'img/vungtau_hoaianhhotel.jpg')
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00006', N'Minh Hien', N'Vung Tau', N'img/vungtau_minhhienhotel.jpg')
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00007', N'Katy Halley', N'Ho Chi Minh', N'img/hcm_khhotel.jpg')
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00008', N'An Hoa', N'Nha Trang', N'img/nhatrang_anhoahotel.jpg')
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00009', N'Aaron', N'Nha Trang', N'img/nhatrang_aaronhotel.jpg')
INSERT [dbo].[Hotel] ([HotelID], [HotelName], [Area], [Image]) VALUES (N'HT00010', N'Bien Vang', N'Vung Tau', N'img/vungtau_bienvanghotel.jpg')
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00001', N'RT001', 50, 15)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00001', N'RT003', 250, 5)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00003', N'RT002', 150, 12)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00003', N'RT003', 200, 3)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00004', N'RT001', 80, 20)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00007', N'RT001', 70, 20)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00007', N'RT002', 130, 13)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00007', N'RT003', 200, 8)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00008', N'RT002', 160, 10)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00008', N'RT003', 300, 2)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00010', N'RT001', 50, 15)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00010', N'RT002', 100, 10)
INSERT [dbo].[HotelRoom] ([HotelID], [RoomTypeID], [Price], [MaxQuantity]) VALUES (N'HT00010', N'RT003', 150, 5)
INSERT [dbo].[RoomType] ([RoomTypeID], [RoomType]) VALUES (N'RT001', N'Single')
INSERT [dbo].[RoomType] ([RoomTypeID], [RoomType]) VALUES (N'RT002', N'Double')
INSERT [dbo].[RoomType] ([RoomTypeID], [RoomType]) VALUES (N'RT003', N'Family')
INSERT [dbo].[User] ([UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'hienht@gmail.com', N'123456', N'The Hien', N'1111111111', N'26/14 Do Quang Dau', N'Admin', 1, CAST(N'2020-01-01' AS Date))
INSERT [dbo].[User] ([UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'khoaphd@gmail.com', N'123456', N'Dang Khoa', N'2222222222', N'10/29 Thong Nhat', N'User', 1, CAST(N'2020-01-01' AS Date))
INSERT [dbo].[User] ([UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'nhunnq@gmail.com', N'123456', N'Quynh Nhu', N'3333333333', N'23 Van Kiep', N'User', 1, CAST(N'2020-01-01' AS Date))
INSERT [dbo].[User] ([UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'phongnlt@gmail.com', N'123456', N'Trung Phong', N'4444444444', N'14-16 Le Lai', N'User', 1, CAST(N'2020-01-01' AS Date))
INSERT [dbo].[User] ([UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'quanghn@gmail.com', N'123456', N'Nhut Quang', N'5555555555', N'251/8 Nguyen Van Troi', N'User', 1, CAST(N'2020-01-01' AS Date))
INSERT [dbo].[User] ([UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'testuser@gmail.com', N'123456', N'Test', N'1234567890', N'ABC', N'User', 1, CAST(N'2020-03-26' AS Date))
INSERT [dbo].[User] ([UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'trangcq@gmail.com', N'123456', N'Quynh Trang', N'6666666666', N'15Bis Luong Huu Khanh', N'User', 1, CAST(N'2020-01-01' AS Date))
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_Booking_Discount] FOREIGN KEY([DiscountCode])
REFERENCES [dbo].[Discount] ([Code])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_Booking_Discount]
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_Booking_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_Booking_User]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_Booking] FOREIGN KEY([BookingID])
REFERENCES [dbo].[Booking] ([BookingID])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_Booking]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_Hotel] FOREIGN KEY([HotelID])
REFERENCES [dbo].[Hotel] ([HotelID])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_Hotel]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_RoomType] FOREIGN KEY([RoomTypeID])
REFERENCES [dbo].[RoomType] ([RoomTypeID])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_RoomType]
GO
ALTER TABLE [dbo].[HotelRoom]  WITH CHECK ADD  CONSTRAINT [FK_HotelRoom_Hotel] FOREIGN KEY([HotelID])
REFERENCES [dbo].[Hotel] ([HotelID])
GO
ALTER TABLE [dbo].[HotelRoom] CHECK CONSTRAINT [FK_HotelRoom_Hotel]
GO
ALTER TABLE [dbo].[HotelRoom]  WITH CHECK ADD  CONSTRAINT [FK_HotelRoom_RoomType] FOREIGN KEY([RoomTypeID])
REFERENCES [dbo].[RoomType] ([RoomTypeID])
GO
ALTER TABLE [dbo].[HotelRoom] CHECK CONSTRAINT [FK_HotelRoom_RoomType]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [CK_BookingDetail] CHECK  (([Price]>(0)))
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [CK_BookingDetail]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [CK_BookingDetail_1] CHECK  (([Quantity]>(0)))
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [CK_BookingDetail_1]
GO
ALTER TABLE [dbo].[Discount]  WITH CHECK ADD  CONSTRAINT [CK_Discount] CHECK  (([Value]>(0) AND [Value]<=(1)))
GO
ALTER TABLE [dbo].[Discount] CHECK CONSTRAINT [CK_Discount]
GO
ALTER TABLE [dbo].[HotelRoom]  WITH CHECK ADD  CONSTRAINT [CK_HotelRoom] CHECK  (([Price]>(0)))
GO
ALTER TABLE [dbo].[HotelRoom] CHECK CONSTRAINT [CK_HotelRoom]
GO
ALTER TABLE [dbo].[HotelRoom]  WITH CHECK ADD  CONSTRAINT [CK_HotelRoom_1] CHECK  (([MaxQuantity]>(0)))
GO
ALTER TABLE [dbo].[HotelRoom] CHECK CONSTRAINT [CK_HotelRoom_1]
GO
USE [master]
GO
ALTER DATABASE [HotelManagement] SET  READ_WRITE 
GO
