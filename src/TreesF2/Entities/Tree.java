package TreesF2.Entities;

import Auxiliar.MyArrayList;
import TreesF2.Entities.Citizen;

public interface Tree {


    void addCitizen (Citizen citizen); //Adds a node to the tree

    void removeCitizen (long citizenId); //Removes a node from the tree

    void printRepresentation(); //Prints all the nodes in the tree with the corresponding branches

    Citizen findCitizenById (long citizenId); //Given an id, it returns the Citizen object that has that id

    Citizen findWitchByWoodAndStone(Object object);
    MyArrayList<Citizen> findWitchByDuck(Object object);

    //Other methods have to be added...

    void findCitizensInRange(float max, float min);

}
