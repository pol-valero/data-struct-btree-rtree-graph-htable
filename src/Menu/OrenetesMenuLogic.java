package Menu;

import GraphsF1.Algorithms.BFS;
import GraphsF1.Algorithms.Dijkstra;
import GraphsF1.Algorithms.MSTprim;
import GraphsF1.Entities.Graph;
import GraphsF1.Entities.PlaceOfInterest;
import GraphsF1.Entities.Swallow;

public class OrenetesMenuLogic {

	public static void showKingdomExploration() {
		Graph graph = new Graph(Menu.GRAPHS_DATASET);
		int nodeID = Menu.askForInteger("Quin lloc vol explorar? ", 0, Integer.MAX_VALUE);
		PlaceOfInterest currentNode = graph.getPlaceByID(nodeID);

		if (currentNode != null) {
			System.out.println(Menu.separator + currentNode.showInformation() + Menu.separator);
			System.out.println("Els llocs del Regne de " + currentNode.getKingdom() + " als que es pot arribar són:" + Menu.separator);

			BFS.kingdomExploration(graph, currentNode);
		}
		else {
			System.out.println(Menu.separator + "El lloc seleccionat no existeix.");
		}
	}

	public static void showFrequentRoutesDetection() {
		Graph graph = new Graph(Menu.GRAPHS_DATASET);
		System.out.println(Menu.separator + "Conjunt de trajectes que connecten tots els llocs i minimitzen la distància total: " + Menu.separator);
		MSTprim.frequentRoutesDetection(graph);
	}

	public static void showPremiumMessaging() {
		Graph graph = new Graph(Menu.GRAPHS_DATASET);
		int nodeID1 = Menu.askForInteger("Quin és el lloc d'origen? ", 0, Integer.MAX_VALUE);

		PlaceOfInterest firstNode = graph.getPlaceByID(nodeID1);
		int nodeID2 = Menu.askForInteger("Quin és el lloc de destí? ", 0, Integer.MAX_VALUE);

		PlaceOfInterest secondNode = graph.getPlaceByID(nodeID2);
		boolean coco = Menu.askForBoolean("L'oreneta carrega un coco? ");

		Swallow europeanSwallow = new Swallow(Swallow.EUROPEA, coco);
		Swallow africanSwallow = new Swallow(Swallow.AFRICANA, coco);

		if (firstNode != null && secondNode != null) {

			PlaceOfInterest[] europeanWay = Dijkstra.premiumMessaging(graph, firstNode, secondNode, europeanSwallow);
			PlaceOfInterest[] africanWay = Dijkstra.premiumMessaging(graph, firstNode, secondNode, africanSwallow);

			if (europeanSwallow.getTotalTime() < africanSwallow.getTotalTime()) {
				showOutput(europeanSwallow, europeanWay);
			} else {
				showOutput(africanSwallow, africanWay);
			}

		}
		else {
			System.out.println(Menu.separator + "Un dels llocs seleccionats no existeix.");
		}
	}

	private static void showOutput(Swallow swallow, PlaceOfInterest[] way) {
		System.out.println(Menu.separator + "L'opció més eficient és enviar una oreneta " + swallow.getType() + "." + Menu.separator);
		System.out.println("\tTemps: " + swallow.getTotalTime());
		System.out.println("\tDistància: " + swallow.getTotalDist());
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