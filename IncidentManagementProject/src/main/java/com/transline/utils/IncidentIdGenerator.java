//package com.transline.utils;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.Year;
//
//public class IncidentIdGenerator {
//
//	private static final String PREFIX = "DTC";
//	private static final String DB_URL = "jdbc:sqlserver://192.168.10.112:1433;databaseName=IncidentManagementProject;encrypt=false";
//	private static final String DB_USER = "sa";
//	private static final String DB_PASSWORD = "admin@123";
//
//	public static synchronized String generateIncidentId() {
//		int year = Year.now().getValue();
//		String keyword = PREFIX + year;
//		int sequenceNumber = getNextSequenceNumber(keyword);
//		return String.format("%s%05d", keyword, sequenceNumber);
//	}
//
//	private static int getNextSequenceNumber(String idPrefix) {
//		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//
//			// Prepare statements
//			String selectSql = "SELECT MAX(incident_id) id FROM incidents WHERE incident_id LIKE ?";
//			// String updateSql = "UPDATE incidents SET sequence_number = sequence_number +
//			// 1 WHERE year = ?";
//
//			try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
//				selectStmt.setString(1, idPrefix + "%");
//				try (ResultSet rs = selectStmt.executeQuery()) {
//					String prevId = null;
//					if (rs.next()) {
//						prevId = rs.getString("id");
//
//					}
//
//					if (prevId == null) {
//						prevId = idPrefix + "00000";
//					}
//
//					// Now You got PrevId
//					int nextSeqNo = Integer.parseInt(prevId.substring(idPrefix.length())) + 1;
//					return nextSeqNo;
//
//				}
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException("Failed to generate incident ID", e);
//		}
//	}
//}
//
////@Component
////public class IncidentIdGenerator {
////
////	private static final String PREFIX = "DTC";
////	private final IncidentRepository incidentRepository;
////
////	@Autowired
////	public IncidentIdGenerator(IncidentRepository incidentRepository) {
////		this.incidentRepository = incidentRepository;
////	}
////
////	public synchronized String generateIncidentId() {
////		int year = Year.now().getValue();
////		String keyword = PREFIX + year;
////		int sequenceNumber = getNextSequenceNumber(keyword);
////		return String.format("%s%05d", keyword, sequenceNumber);
////	}
////
////	private int getNextSequenceNumber(String idPrefix) {
////		String prevId = incidentRepository.findMaxIncidentId(idPrefix + "%");
////		if (prevId == null) {
////			prevId = idPrefix + "00000";
////		}
////		int nextSeqNo = Integer.parseInt(prevId.substring(idPrefix.length())) + 1;
////		return nextSeqNo;
////	}
////}