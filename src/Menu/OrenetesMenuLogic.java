package Menu;

import Algorithms.BFS;
import Algorithms.dijkstra;
import Entities.Graph;
import Entities.PlaceOfInterest;
import Entities.Swallow;

import static Entities.Climate.POLAR;
import static Entities.Climate.TROPICAL;

public class OrenetesMenuLogic {

	public static void showKingdomExploration(int graphSize, Graph graph) {
		int nodeID = Menu.askForInteger("Quin lloc vol explorar? ", 0, Integer.MAX_VALUE);
		PlaceOfInterest currentNode = graph.getPlaceByID(nodeID);

		if (currentNode != null) {
			System.out.println(Menu.separator + currentNode.showInformation() + Menu.separator);
			System.out.println("Els llocs del Regne de " + currentNode.getKingdom() + " als que es pot arribar són:" + Menu.separator);

			BFS.kindgomExploration(graph, currentNode);
		}
		else {
			System.out.println(Menu.separator + "El lloc seleccionat no existeix.");
		}


	}

	public static void showPremiumMessaging(int size, Graph graph) {
		int nodeID1 = Menu.askForInteger("Quin és el lloc d'origen", 0, Integer.MAX_VALUE);
		PlaceOfInterest firstNode = graph.getPlaceByID(nodeID1);
		int nodeID2 = Menu.askForInteger("Quin és el lloc d'origen", 0, Integer.MAX_VALUE);
		PlaceOfInterest secondNode = graph.getPlaceByID(nodeID2);
		boolean coco = Menu.askForBoolean("L'oreneta carrega un coco? ");

		Swallow swallow1 = new Swallow(TROPICAL, coco);
		Swallow swallow2 = new Swallow(POLAR, coco);

		if (firstNode != null && secondNode != null) {
			System.out.println(Menu.separator+"L'opció més eficient és enviar una oreneta " + Menu.separator);

			dijkstra.premiumMessaging(graph, firstNode, secondNode, swallow1);
			dijkstra.premiumMessaging(graph, firstNode, secondNode, swallow2);
		}
		else {
			System.out.println(Menu.separator + "Un dels llocs seleccionats no existeix.");
		}
	}
}
