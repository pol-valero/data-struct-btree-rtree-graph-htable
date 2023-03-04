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

		Swallow europeanSwallow = new Swallow(TROPICAL, coco);
		Swallow africanSwallow = new Swallow(POLAR, coco);

		if (firstNode != null && secondNode != null) {

			PlaceOfInterest[] europeanWay = dijkstra.premiumMessaging(graph, firstNode, secondNode, europeanSwallow);
			PlaceOfInterest[] africanWay = dijkstra.premiumMessaging(graph, firstNode, secondNode, africanSwallow);

			if (europeanSwallow.getTotalTime() < africanSwallow.getTotalTime()) {
				showOutput(europeanSwallow, "europea", europeanWay);
			} else {
				showOutput(africanSwallow, "africana", africanWay);
			}

		}
		else {
			System.out.println(Menu.separator + "Un dels llocs seleccionats no existeix.");
		}
	}

	private static void showOutput(Swallow swallow, String typeSwallow, PlaceOfInterest[] way) {
		System.out.println(Menu.separator+"L'opció més eficient és enviar una oreneta "+typeSwallow+"." + Menu.separator);
		System.out.println("\tTemps: " + swallow.getTotalTime());
		System.out.println("\tDistància: "+ swallow.getTotalDist());
		System.out.println("\tCamí: ");
		int counter = 1;
		if (way != null) {
			for (PlaceOfInterest placeOfInterest : way) {
				if (placeOfInterest != null) {
					System.out.println("\t\t" + (counter) + ". " + placeOfInterest.getName());
					counter++;
				}
			}
		}

	}
}
