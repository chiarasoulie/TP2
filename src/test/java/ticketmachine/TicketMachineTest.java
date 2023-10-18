package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}
	@Test
	// S3 : on n'imprime pas le ticket si le montant inséré est insuffisant
	void noPrintTicketMoneyNotGood(){
		machine.insertMoney(10);
		 // montant pas suffisant
		assertFalse(machine.printTicket(), "Le montant inséré est insuffisant");
	}

	@Test
	// S4 : on imprime le ticket si le montant inséré est suffisant
	void printTicket(){
		machine.insertMoney(50);
		// montant suffisant
		assertTrue(machine.printTicket(),"Le montant inséré est suffisant");

	}
	@Test
	// S5 : quand on imprime le ticket, la balance est décrémentée du prix du ticket
	void printTicketBalanceGood(){
		machine.insertMoney(90);
		machine.printTicket();
		//Le prix est décrémenté
		assertEquals(90-50,machine.getBalance(),"La balance n'est pas à jour");
	}
	@Test
	// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void amountIsGood(){
		machine.insertMoney(90);
		assertEquals(0,machine.getTotal(), "Le prix ne doit pas être à jour");
		machine.printTicket();
		assertEquals(50,machine.getTotal(),"Le montant collecté n'est pas mis à jour");

	}
	@Test
	// S7 : refund() rend correctement la monnaie
	void returnMoneyGood(){
		machine.insertMoney(90);
		machine.printTicket();
		assertEquals(90-50,machine.refund(),"La machine ne rend pas correctement la monnaie");
	}
	@Test
	//S8 : refund() remet la balance à zéro
	void returnBalanceZero(){
		machine.insertMoney(50);
		machine.refund();
		assertEquals(0,machine.getBalance(),"La balance n'a pas été remise à zéro");

	}
	@Test
	// S9 : on ne peut pas insérer un montant négatif
	void notInsertNegativeValues(){
		try{
			machine.insertMoney(-50);
			fail("doit lever une exception");
		} catch(IllegalArgumentException a) {

		}
	}
	@Test
	// S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	void noPriceNegative(){
		TicketMachine machine = new TicketMachine(-20);
		assertThrows(IllegalArgumentException.class,()->{machine.getPrice();},"Le prix du ticket ne peut pas être négatif");
	}


	}


