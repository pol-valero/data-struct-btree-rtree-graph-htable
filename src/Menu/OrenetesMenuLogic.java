package Menu;

import Algorithms.BFS;
import Algorithms.MSTprim;
import Algorithms.dijkstra;
import Entities.Graph;
import Entities.PlaceOfInterest;
import Entities.Swallow;
import Entities.myArrayList;

import static Entities.Climate.POLAR;
import static Entities.Climate.TROPICAL;

public class OrenetesMenuLogic {

	public static void showKingdomExploration(int graphSize, Graph graph) {
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

	public static void showFrequentRoutesDetection(Graph graph) {
		System.out.println(Menu.separator + "Conjunt de trajectes que connecten tots els llocs i minimitzen la distància total: " + Menu.separator);
		MSTprim.frequentRoutesDetection(graph);
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


			myArrayList<PlaceOfInterest> europeanWay = dijkstra.premiumMessaging(graph, firstNode, secondNode, europeanSwallow);
			myArrayList<PlaceOfInterest> africanWay = dijkstra.premiumMessaging(graph, firstNode, secondNode, africanSwallow);

			//if (europeanSwallow.getTotalDist() < africanSwallow.getTotalDist()) {
				System.out.println(Menu.separator+"L'opció més eficient és enviar una oreneta europea." + Menu.separator);
				System.out.println(Menu.separator+"\tTemps: ");
				System.out.println(Menu.separator+"\tDistància: "+ europeanSwallow.getTotalDist());
				System.out.println(Menu.separator+"\tCamí: ");
				for (int i = 0; i < europeanWay.size(); i++) {
					System.out.println("\t"+(i+1)+". "+europeanWay.get(i).getName());
				}
			/*} else {
				System.out.println(Menu.separator+"L'opció més eficient és enviar una oreneta africana." + Menu.separator);
				System.out.println(Menu.separator+"\tTemps: ");
				System.out.println(Menu.separator+"\tDistància: "+ africanSwallow.getTotalDist());
				System.out.println(Menu.separator+"\tCamí: ");
				for (int i = 0; i < africanWay.size(); i++) {
					System.out.println("\t"+(i+1)+". "+africanWay.get(i));
				}
			}*/
		}
		else {
			System.out.println(Menu.separator + "Un dels llocs seleccionats no existeix.");
		}
	}
}
