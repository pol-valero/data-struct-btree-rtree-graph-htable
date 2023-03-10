package TreesF2.Entities;

import TreesF2.Entities.Citizen;

public interface Tree {


    void addCitizen (Citizen citizen); //Adds a node to the tree

    void removeCitizen (long citizenId); //Removes a node from the tree

    void printRepresentation(); //Prints all the nodes in the tree with the corresponding branches

    Citizen findCitizenById (long citizenId); //Given an id, it returns the Citizen object that has that id

    //Other methods have to be added...

}
