package dao;

import models.Club;

import java.util.List;

public class TestClubDAO {
    public static void main(String[] args) {
//        getAllClubsTest();
//        getClubByIdTest();
//        createClubTest();
//        getTopClubsTest();
        updateClubTest();
    }

    public static List<Club> getAllClubsTest() {
        ClubDAOImp clubDAOImp = new ClubDAOImp();
        List<Club> clubs = clubDAOImp.getAllClubs();
        for (Club club : clubs) {
            System.out.println(club);
        }
        return clubs;
    }

    public static Club getClubByIdTest() {
        ClubDAOImp clubDAOImp = new ClubDAOImp();
        int clubId = 1; // Use a valid ID for testing
        Club club = clubDAOImp.getClubById(clubId);
        if (club != null) {
            System.out.println("Club found: " + club);
        } else {
            System.out.println("Club not found with ID: " + clubId);
        }
        return club;
    }

    public static void createClubTest() {
        ClubDAOImp clubDAOImp = new ClubDAOImp();
        Club club = new Club(0, 0, "New Club"); // ID_CLUB is auto-incremented
        boolean isCreated = clubDAOImp.createClub(club);
        if (isCreated) {
            System.out.println("Club created successfully: " + club);
        } else {
            System.out.println("Failed to create club: " + club);
        }
    }

    public static List<Club> getTopClubsTest() {
        ClubDAOImp clubDAOImp = new ClubDAOImp();
        int topNumber = 5; // Number of top clubs to retrieve
        List<Club> clubs = clubDAOImp.getTopClubs(topNumber);
        for (Club club : clubs) {
            System.out.println(club);
        }
        return clubs;
    }

    public static void updateClubTest() {
        ClubDAOImp clubDAOImp = new ClubDAOImp();
        int clubId = 1; // Use a valid ID for testing
        Club club = clubDAOImp.getClubById(clubId);
        if (club != null) {
            club.setNom("Updated Club Name");
            Club updatedClub = clubDAOImp.updateClub(club);
            if (updatedClub != null) {
                System.out.println("Club updated successfully: " + updatedClub);
            } else {
                System.out.println("Failed to update club: " + club);
            }
        } else {
            System.out.println("Club not found with ID: " + clubId);
        }
    }
}
