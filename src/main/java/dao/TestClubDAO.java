package dao;

import models.Club;
import models.Membre;
import models.User;

import java.util.List;

public class TestClubDAO {
    public static void main(String[] args) {
//        getAllClubsTest();
//        getClubByIdTest();
//        createClubTest();
//        getTopClubsTest();
//        updateClubTest();
//        getMembersCountByClubId();
//        testGetClubsOfMember();
//        testGetClubsOfManager();
        testUserIsMemberOfClub();
    }
    public static void testUserIsMemberOfClub(){
        ClubDAO clubDAO = new ClubDAOImp();
        User user = new User();
        user.setId_user(4);
        Club club = new Club();
        club.setIdClub(4);
        if(clubDAO.userIsMemberOfClub(user,club)){
            System.out.println("user is part of the club");
        }else{
            System.out.println("user is not a part of the club");
        }
    }
    public static void testGetClubsOfManager(){
        ClubDAO clubDAO = new ClubDAOImp();
        Membre membre = new Membre();
        membre.setIdMembre(1);
        List<Club> clubs = clubDAO.getClubsOfManager(membre);
        System.out.println(clubs);
    }

    public static List<Club> getAllClubsTest() {
        ClubDAOImp clubDAOImp = new ClubDAOImp();
        List<Club> clubs = clubDAOImp.getAllClubs();
        for (Club club : clubs) {
            System.out.println(club);
        }
        return clubs;
    }
    public static void testGetClubsOfMember() {
        ClubDAOImp clubDAOImp = new ClubDAOImp();
        int memberId = 1; // Replace with an existing member ID for testing
        List<Club> clubs = clubDAOImp.getClubsOfMember(memberId);

        System.out.println("Clubs of Member with ID " + memberId + ":");
        for (Club club : clubs) {
            System.out.println("ID_CLUB: " + club.getIdClub() + ", ID_USER: " + club.getIdUser() + ", NOM: " + club.getNom());
        }
    }
    public static int getMembersCountByClubId() {
        ClubDAOImp clubDAOImp = new ClubDAOImp();
        int clubId = 1; // Use a valid ID for testing
        int count = clubDAOImp.getMembersCountByClubId(clubId);
        if (count != 0) {
            System.out.println("" + count);
        } else {
            System.out.println("" + count);
        }
        return count;
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
        Club club = new Club(0, 1, "New Club"); // ID_CLUB is auto-incremented
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
