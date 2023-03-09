package TreesF2.Entities;

public interface Tree {

    void addCitizen (Citizen citizen);

    void removeCitizen (long citizenId);

    void printRepresentation();

    Citizen findCitizenById (long citizenId);

    //Other methods have to be added...

}
