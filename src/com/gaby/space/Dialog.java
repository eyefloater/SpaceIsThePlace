package com.gaby.space;

import java.util.Scanner;

//dialog trees, with multiple choice responses, to keep things simple. 

//Nico: Sets up plot with conversation about how sick you both are of the moon, and that, incidentally, the Jupiter cruise liner's house band were all found dead of Neptune Juice overdoses last night. Maybe we can get a band together.. where's my ID? (wallet was empty, need ID to get past Charlie.

//Charlie (guy at checkpoint): do you have ID? what's your band's name? (if not Nico's suggestion, won't let you through.

//Aggie: make peace with old friend, ask if she will join as bass player (yes, but not if band named by Nico. suggests name. also, needs DRUMS first. gives you credit stick with half the money for drums.)

//Hugo, mining foreman, won't hire you permanently cos you really messed it up last time, why are you even on this damn rock if you hate it so much , btw i'll pay you to recover ROVER

//shop guy. sells you DRUMS if you have enough money. suggests you "get a damn job mining, miner"

//guy w saxophone. longwinded story of Sun Ra. gives you SAX.

//Louis, Jupiter Club manager, books bands for cruise ship. got band and gear? ok you can rehearse. listens after rehearsal. hires if you give Aggie's bandname. gives you laminated employee LANYARDS for cruise ship.

//cruise ship guy. no tree here, just 'welcome, here are your luxurious quarters, be on stage at 8pm, blast off...

public class Dialog {

	private static int nicoCounter;
	private static int aggieCounter;
	private static int louisCounter;

	public static int getNicoCounter() {
		return nicoCounter;
	}
	public static int getAggieCounter() {
		return aggieCounter;
	}
	public static int getLouisCounter() {
		return louisCounter;
	}

	public static void setNicoCounter(int nicoCounter) {
		Dialog.nicoCounter = nicoCounter;
	}
	public static void setAggieCounter(int aggieCounter) {
		Dialog.aggieCounter = aggieCounter;
	}
	public static void setLouisCounter(int louisCounter) {
		Dialog.louisCounter = louisCounter;
		
	}

	public static void talk(String npc) {
		if (npc.equals("nico")) {
			if (Character.hasBigIdea()) {
				nico3();
			}
			if (nicoCounter == 2) {
				nico2();
			}
			if (nicoCounter == 1) {
				nico1();

			}

		}
		if (npc.equals("charlie")) {
			charlie();
		}
		if (npc.equals("hugo")) {
			if (Character.hasRover()) {
				hugo2();
			} else {
				hugo1();
			}
		}

		if (npc.equals("aggie")) {
			if(aggieCounter == 2) {
			aggie2();
		}
			if(aggieCounter == 1) {
			aggie1();
			}
		}

		if (npc.equals("louis")) {
			if(louisCounter == 2) {
			louis2();
		}
			if(louisCounter == 1) {
			louis1();
			}
		}
		if (npc.equals("shopkeeper")) {
			shopkeeper();
		}
		if (npc.equals("sun ra")) {
			sunRa();
		}
		if (npc.equals("captain")) {
			captain();
		}
	}

	public static void nico1() {

		System.out
				.println("Nico's been very quiet lately. You wave to him amiably, and he looks up mid-scrub. \nGOO, I've decided. I need - we need - to get the hell off this rock before we both go loco en el coco. \nWhen was the last time you spoke to someone besides me or Charlie. \nThat wasn't a robot? I swear to el cabron Elon Musk, we need to get the hell out of here. \nYou know there's a Jupiter Line Cruiser in dock this week? It leaves tomorrow. \nI'd rob a bank to get us both on that ship, GOO. Ganymede City, primo mio. That's the place to be. \nNo more depressed ex-miners, no more fake ocean drilling into my ears all night. A real live city, man. What do you say?");
		Scanner scan = new Scanner(System.in);
		System.out.println("");
		System.out
				.println("Obviously Nico had a lot to get off his chest. GOO can't argue with the sentiment.");
		System.out.println("");
		System.out
				.println("GOO: Yeah, I hear you cuz, and I'd rob a bank too, if there were any left here. \nBut the Jupiter Liner is just here to refuel; \nthere's not a soul in the Zone with the credits to buy a \nticket onto that lovely, lovely flying pleasure cube.");
		System.out
				.println("Nico: Well we need to figure out something. \nI have 45 glasses left to wash, after that, I don't know - \nI'm about ready to take a little moon stroll without my spacesuit on, \nif you catch my drift");
		System.out.println("");
		System.out.println("Select GOO's response:");
		System.out
				.println("1: Just forget it Nico. Maybe I can get my mining job back and start saving up.");
		System.out
				.println("2: Ok, calm down cuz. I'm with you. \nWe'll find a way onto that ship. \nJust don't take any moon strolls tonight, ok?");
		Scanner scanner = new Scanner(System.in);
		int answer = scan.nextInt();
		if (answer == 1) {
			System.out
					.println("Nico: You know it'll be another six months before another ship comes to the Zone, GOO. \nI can't wait that long.");

		}
		if (answer == 2) {
			System.out
					.println("Nico: That's good to hear, primo mio. \nWhy don't you go take a sniff around, maybe they need line cooks or something.");

		}

		// else {
		// System.out.println("Please enter a valid number!");

		// }
		nicoCounter = 2;
	}

	public static void nico2() {
		System.out.println("Nico looks a little more cheerful right now.");

		System.out.println("Select GOO's response:");
		System.out.println("1: Hey Nico.");
		System.out.println("2: Hey Nico, have you seen my ID badge?");
		Scanner scan = new Scanner(System.in);
		int response = scan.nextInt();
		if (response == 1) {
			System.out.println("Que? Oh yah, hi. Nice night for a swim huh?");
		}
		if (response == 2) {
			System.out
					.println("Yeah, idiota, you dropped it while you and Aggie were dancing last night. \nHere you go. We're is that loca anyway?\nNico hands you your ID badge.");
			Character.hasID();
			Commands.takeObject("take id");
			nicoCounter = 0;
		}
		// else {
		// System.out.println("Please enter a valid number!");

		// }

	}

	public static void nico3() {
		System.out
				.println("GOO: Ok, Nico, this is gonna sound crazy, but hear me out, ok? \nI think I have an idea.");
		System.out.println("Nico: I'm listening.");
		System.out
				.println("GOO: Just got word from Charlie that the Jupiter's house band keeled over en masse last night. \nNeptune juice. Just tell me one thing, cuz I gotta split. \nYou still have your bass rig? I know it's been a while..");
		System.out
				.println("Nico: Coño! That's some news. I hear ya cluckin', primo mio. I have the rig. \nBut if we're going to do what I think you wanna do, \nwe're going to need a drummer, and a rehearsal space, and hombre.. \ndo you even play anymore? You sold your sax like 5 years ago.");
		System.out
				.println("GOO: I'll figure it out, don't worry. Just start practicing!");
		System.out
				.println("NICO: Will do. Hey, what do you think about the Neptune Juicers? That's pretty good right? Dark. But cool.");
		Scanner scan = new Scanner(System.in);
		System.out.println("1: That's a terrible band name, Nico.");
		System.out.println("2: That's an amazing band name, Nico.");
		int response = scan.nextInt();
		if (response == 1) {
			System.out
					.println("Nico frowns and turns back to his glasses. \"Whatever man, you just don't know space jazz like I know space jazz.\"");
		}
		if (response == 2) {
			System.out
					.println("Nico grins and turns back to his glasses. \"Space jazz, man. Space jazz.\"");
		}
	}

	public static void charlie() {
		if (!Character.hasID()) {
			System.out.println("GOO: Hi Charlie. Let me through, huh?");
			System.out
					.println("Charlie: No can do, GOO. Only with your ID badge.");
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter a response:");
			System.out.println("1: Ok pal. I'll see if I can dig it up.");
			int response = scan.nextInt();
			if (response == 1) {
				System.out.println("Charlie: Alright, GOO.");
			}

			// else {
			// System.out.println("Please enter a valid number!");
			// }
		}
		if (Character.hasID()) {
			System.out
					.println("Hey Charlie, heard anything about that Jupiter Liner?");
			System.out
					.println("Ha, that floating lego's gonna be in dock a while longer. \nI guess they've got they're people crawlin' the zone around trying to figure out how \ntheir entire house band ended up gassin' themselves to \ndeath on Neptune Juice over in sector 3 last night. \nMusicians, huh?");
			System.out
					.println("GOO: Yeah, huh. Musicians. Glad I'm uhh, not one of those.");
			System.out.println("");
			System.out
					.println("GOO begins to get an idea. \nBut he's not about to spill it to Charlie. \nNo need to get laughed at for the ridicul...but maybe just crazy enough to work? \nIf he could just put a band together.");
			Character.setHasBigIdea();
		}
	}

	public static void aggie1() {
		System.out
				.println("GOO: Um, hey Aggie. How's life in the abandoned oil refinery these days? Hey! Slow down, I just wanna talk!");
		System.out
				.println("Aggie: What's it to you, GOO? Moon life is amazing. \nI snuck into Hugo's bathroom and took a 4 hour long shower this morning. So I'm doin' great. \nWhat's it to you, Mr. I-Won't-Mine-Anymore-Because-Robots-Have-Rights-Too, et fuckin' cetera? \nIf you're here to talk me out of my abandoned oil refinery life, I'm not interested, lover boy.");
		System.out.println("Enter a response:");
		System.out
				.println("1: Robots do have rights! And feelings! Why don't you read Marx with me anymore?");
		System.out
				.println("2: Ha, no need to apologize, buddy. I was just wondering if you wanted to be in a band with me again.");
		Scanner scn = new Scanner(System.in);
		int answer = scn.nextInt();
		if (answer == 1) {
			System.out
					.println("GOO knows better than to say that. \nInstead, he says: Ha, no need to apologize, buddy. \nI was just wondering if you wanted to be in a band with me again.");
			if (answer == 2) {
				System.out
						.println("2: Ha, no need to apologize, buddy. \nI was just wondering if you wanted to be in a band with me again.");
			}
			System.out
					.println("Aggie: I'd love to, as long as it's space jazz.");
			System.out.println("Goo: Space is the place, buddy.");
			System.out
					.println("Aggie: Space is totally the place. I don't have drums though. \nAnd what's this band going to be called?");
			System.out
					.println("Goo: Well Nico suggested The, ummm, The Neptune Juicers.");
			System.out
					.println("Aggie(aghast): That's a terrible name and you know it, GOO.");
			System.out
					.println("Goo: Yeah, I know. Anyway, think of a name. I'm going to see if I can find some drums.");
			System.out.println("Aggie: TTYL");
			aggieCounter = 2;
		}
	}

	public static void aggie2(){
		System.out
		.println("GOO: Aggie, I've got drums. Meet me at the Jupiter Club ASAP.");
		System.out
		.println("Aggie: Our conversations are getting shorter, have you noticed? But yeah, I'll see you there.");
		
	}
	public static void hugo1() {
		Scanner scn = new Scanner(System.in);
		System.out
				.println("GOO: Hey Hugo, got any work for me? \nI'm trying to raise some money to..well never mind. \nI really use some credits right now. \nAnd I don't drink Neptune Juice anymore, before you ask.");
		System.out
				.println("Hugo (yelling over machine noise): Takes a lot balls to ask me for work after that stunt you pulled last year. \nTook me 6 weeks to get my robots to stop crying to each other. \nBut yeah, as it turns out, I do. \nI've got a prospecting rover out-Zone that's been gone a week. \nMy robots are too scared to go out there. If you bring him back, I'll pay you 50,000 credits. \nYou can buy yourself a shitload of Neptune Juice with that.");
		System.out.println("Enter a response:");
		System.out
				.println("I don't drink Neptune juic..oh never mind. Yeah I'll take the job.");
		System.out.println("2: Sure, I'll take the job.");
		int answer = scn.nextInt();
		if (answer == 1 || answer == 2) {
			System.out
					.println("Ok buddy. Get out there. My goddamn robots are inconsolable over here.");
		}
	}

	public static void hugo2() {
		System.out
				.println("GOO: Hey, here's your rover. Tell your robots he's gonna be ok. \nAnd I don't drink Neptune Juice anymore, before you ask.");
		Commands.dropObject("drop rover");
		System.out
				.println("Hugo (yelling over machine noise): Takes a lot balls to..oh yeah, I forgot. \nAlright, here's your 50,000. \nYou can buy yourself a shitload of Neptune Juice with that.");

		System.out.println("Enter a response:");
		System.out
				.println("I don't drink Neptune..whatver, thanks Hugo. You're a gent.");
		System.out.println("2: Thanks Hugo. You're a gent.");
		Scanner scn = new Scanner(System.in);
		int answer = scn.nextInt();
		if (answer == 1 || answer == 2) {
			System.out
					.println("Hugo hands GOO a credit cube and turns his back immediately to bat at his robots with his shovel, \nwho have now gathered around the little rover in a worried huddled.");
			Character.setHasHugoCredits(true);
			Commands.takeObject("take credit cube");
		}
	}

	public static void shopkeeper() {
		if(Character.hasHugoCredits()){
		System.out.println("GOO: Hi, I'd like to buy those drums.");
		System.out.println("Tyler: Uh sure, 50,000 credits, man.");
		Commands.dropObject("drop credit cube");
		Character.setHasHugoCredits(false);
		System.out.println("Tyler: Thank you very much. \nAs you can see, they fold neatly into this small attache case.");
		System.out.println("Tyler hands you the attache.");
		Commands.takeObject("take drum set");
		Character.setHasDrums(true);}
		else{
			System.out.println("GOO: Hi, I'd like to buy those drums.");
			System.out.println("Tyler: Uh sure, 50,000 credits, man.");
			System.out.println("GOO: Uh, huh. K bye.");
		}
		

		
	}

	public static void sunRa() {
		System.out.println("GOO: Umm, are you Sun Ra? Aren't you like 250 years old?");
		System.out.println("Sun Ra: Yes, and yes. Here, take my saxophone.");
		Commands.takeObject("Take saxophone");
		Character.setHasSaxophone(true);
		System.out.println("GOO takes the saxophone, fits his reed to it, and gives it a blow. \nIt sounds amazing. \nHe can't help but fall into a fit of nervous, wondrous laughter");
		System.out.println("Sun Ra: Ok well this deus ex machina is outta here. \nSun Ra disappears in a burst of radiance. \nSpace is the place, GOO. Never....forget...that!");
		
		Commands.moveSunRaToLunarPlain();
		

	}
	public static void louis1() {
		System.out.println("GOO: Hey Louis, what's happenin'?");
		System.out.println("Louis: Nice jacket GOO. \nWhat's happening is I've got 10 dead Martian musicians on my hands \nand the Jupiter Corp. is on my ass to find replacements.");
		System.out.println("I've got an ace band. Just give me a couple hours to get them over here and play for you");
		System.out.println("Louis: You? I haven't seen you play a horn in 5 years. \nDon't mess with me, I'm stressed out. \nBut if you're serious, I'll be around. Space jazz...it's alright");
		louisCounter = 2;
	}
	public static void louis2() {
		if(Character.hasBigIdea()&&Character.hasSaxophone()&&Character.hasDrums()){
			System.out.println("You approach Louis with your band in tow. \nYou play for him, and he, being an old fan of space jazz himself, is very pleased. \nYou're hired on the spot, and given laminated Jupiter Line employee lanyards");
			Commands.takeObject("lanyards");
		}
		else{System.out.println("Louis: I like you kid, but you're not ready");}
	}

	public static void captain() {
		System.out.println("ARR, welcome aboard");
	}

	

	

}
