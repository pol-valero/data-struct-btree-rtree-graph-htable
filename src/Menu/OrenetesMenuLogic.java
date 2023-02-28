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
		int nodeID1 = Menu.askForInteger("Quin és el lloc d'origen? ", 0, Integer.MAX_VALUE);
		PlaceOfInterest firstNode = graph.getPlaceByID(nodeID1);
		int nodeID2 = Menu.askForInteger("Quin és el lloc de destí? ", 0, Integer.MAX_VALUE);
		PlaceOfInterest secondNode = graph.getPlaceByID(nodeID2);
		boolean coco = Menu.askForBoolean("L'oreneta carrega un coco? ");

		Swallow swallow1 = new Swallow(TROPICAL, coco);
		Swallow swallow2 = new Swallow(POLAR, coco);

		if (firstNode != null && secondNode != null) {


			PlaceOfInterest[] way1 = dijkstra.premiumMessaging(graph, firstNode, secondNode, swallow1);
			PlaceOfInterest[] way2 = dijkstra.premiumMessaging(graph, firstNode, secondNode, swallow2);

			if (swallow1.getTotalDist() < swallow2.getTotalDist()) {
				System.out.println(Menu.separator+"L'opció més eficient és enviar una oreneta europea." + Menu.separator);
				System.out.println(Menu.separator+"\tTemps: ");
				System.out.println(Menu.separator+"\tDistància: "+ swallow1.getTotalDist());
				System.out.println(Menu.separator+"\tCamí: ");
				for (int i = 0; i < way1.length; i++) {
					System.out.println("\t"+(i+1)+". "+way1[i]);
				}
			} else {
				System.out.println(Menu.separator+"L'opció més eficient és enviar una oreneta africana." + Menu.separator);
				System.out.println(Menu.separator+"\tTemps: ");
				System.out.println(Menu.separator+"\tDistància: "+ swallow2.getTotalDist());
				System.out.println(Menu.separator+"\tCamí: ");
				for (int i = 0; i < way1.length; i++) {
					System.out.println("\t"+(i+1)+". "+way2[i]);
				}
			}
		}
		else {
			System.out.println(Menu.separator + "Un dels llocs seleccionats no existeix.");
		}
	}
}
