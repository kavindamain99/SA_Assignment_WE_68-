package doc24menuproducer;

public class MenuImpl implements Menu {

	@Override
	public void printMainMenu() {
		System.out.println("========================================================");
		System.out.println();
		System.out.println("\t- Doc24 Channeling admin board - ");
		System.out.println();
		System.out.println("1-Insert New Doctor ");
		System.out.println("2-View Doctor List ");
		System.out.println("3-Delete existing Doctor ");
		System.out.println("4-Search existing Doctor ");
		System.out.println();
		System.out.println("========================================================");
		System.out.println("Enter List Number by Menu ");
		
	}

	@Override
	public void printInvalid() {
		System.out.println("Invalid option please enter valid key ");
		
	}

}
