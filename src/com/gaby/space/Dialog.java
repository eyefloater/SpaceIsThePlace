package com.gaby.space;

//dialog trees, although i've given player as few choices as possible cuz this is confusing as heck. not very good OOP, but it works.

//Nico: Sets up plot with conversation about how sick you both are of the moon, and that, incidentally, the Jupiter cruise liner's house band were all found dead of Neptune Juice overdoses last night. Maybe we can get a band together.. where's my ID? (wallet was empty, need ID to get past Charlie.

//Charlie (guy at checkpoint): do you have ID? gives you bigIdea (get job as musician on spaceship

//Aggie: make peace with old friend, ask if she will join as drummer (yes, but not if band named by Nico. suggests name? also, needs DRUMS first. gives you credit stick with half the money for drums.)

//Hugo, mining foreman, won't hire you permanently cos you really messed it up last time, why are you even on this damn rock if you hate it so much , btw i'll pay you to recover ROVER

//shop guy. sells you DRUMS if you have enough money.

//Sun Ra. gives you SAXOPHONE.

//Louis, Jupiter Club manager, books bands for cruise ship. need leather jacket, big idea, drums, and sax to be hired. gives laminated employee LANYARDS for cruise ship.

//cruise ship guy. no tree here, just 'welcome, here are your luxurious quarters, be on stage at 8pm, blast off...

public class Dialog {

	//counters keep track of which conversations have happened so far.
	private static int nicoCounter;
	private static int aggieCounter;
	private static int louisCounter;
	private static int charlieCounter;

	private static String talkingTo;

	public static String getTalkingTo() {
		return talkingTo;
	}

	public static void setTalkingTo(String talkingTo) {
		Dialog.talkingTo = talkingTo;
	}

	public static int getNicoCounter() {
		return nicoCounter;
	}

	public static int getCharlieCounter() {
		return charlieCounter;
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

	public static void setCharlieCounter(int charlieCounter) {
		Dialog.charlieCounter = charlieCounter;

	}

	public static void setAggieCounter(int aggieCounter) {
		Dialog.aggieCounter = aggieCounter;
	}

	public static void setLouisCounter(int louisCounter) {
		Dialog.louisCounter = louisCounter;

	}

	//most complex dialog, as it was the first one i did. the subsequent characters offer a lot less player choice.
	public static void talkToNico(String command) {
		if (command == null) {
			return;
		}
		setTalkingTo("nico");

		if (Character.hasBigIdea()) {
			GUI.clearOptions();
			GUI.addToOptions("GOO: Ok, Nico, this is gonna sound crazy, but hear me out, ok? I think I have an idea.");
			GUI.addToOptions("\nNico: I'm listening.");
			GUI.addToOptions("\nGOO: Just got word from Charlie that the Jupiter Liner's band keeled over en masse last night. Neptune juice. Just tell me one thing, cuz I gotta split. You still have your bass rig? I know it's been a while..");
			GUI.addToOptions("\nNico: Coño! That's some news. I hear ya cluckin', primo mio. I have the rig. But if we're going to do what I think you wanna do, we're going to need a drummer, and hombre.. do you even play anymore? You sold your sax like 5 years ago.");
			GUI.addToOptions("\nGOO: I'll figure it out, don't worry. I'm going to talk to the booking guy at the Jupiter Club. Just start practicing!");
			GUI.addToOptions("\nNico: Will do. Hey, what do you think about the Neptune Juicers? That's pretty good right? Dark. But cool.");
			GUI.addToOptions("\nGOO: Uhh, not...bad Nico.");

			nicoCounter = 4;
			setTalkingTo(null);

		} else if (nicoCounter == 1) {

			GUI.clearOptions();
			GUI.addToOptions("Nico's been very quiet lately. You wave to him amiably, and he looks up mid-scrub. Nico: GOO, I've decided. I need - we need - to get the hell off this rock before we both go loco en el coco. When was the last time you spoke to someone besides me or Charlie? That wasn't a robot? I swear to el gran cabron Elon Musk, we need to get the hell out of here. You know there's a Jupiter Line Cruiser in dock this week? It leaves tomorrow. I'd rob a bank to get us both on that ship, GOO. Ganymede City, primo mio. That's the place to be. No more depressed ex-miners, no more fake ocean drilling into my ears all night. A real live city, man. What do you say?");
			GUI.addToOptions("\n\nObviously Nico had a lot to get off his chest. GOO can't argue with the sentiment.");
			GUI.addToOptions("");
			GUI.addToOptions("\n\nGOO: Yeah, I hear you cuz, and I'd rob a bank too, if there were any left here. But the Jupiter Liner is just here to refuel; there's not a soul in the Zone with the credits to buy a ticket onto that lovely, lovely flying pleasure cube.");
			GUI.addToOptions("\n\nNico: Well we need to figure out something. I have 45 glasses left to wash, after that, I don't know - I'm about ready to take a little moon stroll without my spacesuit on, if you catch my drift");
			GUI.addToOptions("\n\nSelect GOO's response:");
			GUI.addToOptions("\n1: Just forget it Nico. Maybe I can get my mining job back and start saving up.");
			GUI.addToOptions("\n2: Ok, calm down cuz. I'm with you. We'll find a way onto that ship. Just don't take any moon strolls tonight, ok?");
			nicoCounter = 2;
			setTalkingTo("nico");

		} else if (nicoCounter == 2) {
			if (!command.equals("1") && !command.equals("2")) {
				GUI.addToOptions("\n\nEnter a valid response");
				return;
			}
			if (command.equals("1")) {
				GUI.clearOptions();
				GUI.addToOptions("Nico: Si, seguro. Just think about it okay? At least go to talk Charlie, for my sake. He always has his good ear to the ground when there's a ship in port.");
				nicoCounter = 3;
				setTalkingTo("nico");
				return;
			}
			if (command.equals("2")) {
				GUI.clearOptions();
				GUI.addToOptions("Nico: Primo mio, you have no idea how much that chills me out. I promise no moonstrolling. I'm going to stay here and wash glasses, because that's what I do. Go talk to Charlie. He's always got his good ear to the ground when a ship's docked in the zone.");
				nicoCounter = 3;
				setTalkingTo("nico");
				return;

			}

		} else if (nicoCounter == 4) {
			if (!command.equals("1") && !command.equals("2")) {
				GUI.addToOptions("\n\nEnter a valid response");
				return;
			}
			if (command.equals("1")) {
				GUI.clearOptions();
				GUI.addToOptions("Nico: Que? Oh yah, hi. Nice night for a swim huh?");
				setTalkingTo(null);
				return;
			} else if (command.equals("2")) {
				GUI.clearOptions();
				GUI.addToOptions("Nico: Yeah, idiota, you dropped it while you and Aggie were dancing last night. Here you go. We're is that loca anyway? Nico hands you your ID badge.");
				Character.hasID();
				Commands.takeObject("take id");
				nicoCounter = 5;
				setTalkingTo(null);
				return;
			}

		} else if (nicoCounter == 3) {
			GUI.clearOptions();
			GUI.addToOptions("Nico looks a little more cheerful right now.");

			GUI.addToOptions("\n\nSelect GOO's response:");
			GUI.addToOptions("\n1: Hey Nico.");
			GUI.addToOptions("\n2: Hey Nico, have you seen my ID badge?");
			nicoCounter = 4;
			setTalkingTo("nico");

		} else if (nicoCounter == 5) {
			GUI.clearOptions();
			GUI.addToOptions("Nico: Maybe space really *is* the place...");
			setTalkingTo(null);

		}
	}

	public static void talkToCharlie(String command) {
		setTalkingTo("charlie");
		charlie();
	}

	public static void talkToHugo(String command) {
		setTalkingTo("hugo");
		if (Character.hasRover() && Character.getLocation() == 11) {
			GUI.clearOptions();
			GUI.addToOptions("GOO: Hey, here's your rover. Tell your robots he's gonna be ok. And I don't drink Neptune Juice anymore, before you ask.\n");
			Commands.dropObject("drop rover");
			GUI.addToOptions("\n\nHugo (yelling over machine noise): Takes a lot balls to..oh yeah, I forgot. Alright, here's your 50,000. You can buy yourself a shitload of Neptune Juice with that.");
			GUI.addToOptions("\nGOO: I don't drink Neptune..whatver, thanks Hugo. You're a gent.");
			Commands.takeObject("take credit cube");

		} else {
			GUI.clearOptions();
			GUI.addToOptions("GOO: Hey Hugo, got any work for me? I'm trying to raise some money to..well never mind. I could really use some credits right now. And I don't drink Neptune Juice anymore, before you ask.");
			GUI.addToOptions("\nHugo (yelling over machine noise): Takes a lot balls to ask me for work after that stunt you pulled last year. Took me 6 weeks to get my robots to stop crying to each other. But yeah, as it turns out, I do. I've got a prospecting rover out-Zone that's been gone a week. My robots are too scared to go out there. If you bring him back, I'll pay you 50,000 credits. You can buy yourself a shitload of Neptune Juice with that.");
			GUI.addToOptions("\nGOO: I don't drink Neptune juic..oh never mind. But yeah. I'll take the job.");
			GUI.addToOptions("\nHugo, okay, just don't take all night. My goddamn robots are inconsolable over here.");
			setTalkingTo(null);

		}
	}

	public static void talkToAggie(String command) {
		setTalkingTo("aggie");
		if (aggieCounter == 1) {
			GUI.clearOptions();
			GUI.addToOptions("GOO: Um, hey Aggie. How's life in the abandoned oil refinery these days? Hey! Slow down, I just wanna talk!");
			GUI.addToOptions("\nAggie: What's it to you, GOO? Moon life is amazing. I snuck into Hugo's bathroom and took a 4 hour long shower this morning. So I'm doin' great, Mr. I-Won't-Mine-Anymore-Because-Robots-Have-Rights-Too, et fuckin' cetera? If you're here to talk me out of my abandoned oil refinery life, I'm not interested, lover boy.");
			GUI.addToOptions("\nGOO: Ha, Aggie, I think I'm done with my Marxist period. Anyway, I was just wondering if you wanted to be in a band with me again.");
		}
		if (aggieCounter == 2) {
			GUI.addToOptions("Aggie: I'd love to, as long as it's space jazz.");
			GUI.addToOptions("\nGoo: Space is the place, buddy.");
			GUI.addToOptions("\nAggie: Space is totally the place. I don't have drums though. And what's this band going to be called?");
			GUI.addToOptions("\nGoo: Well Nico suggested The, ummm, The Neptune Juicers.");
			GUI.addToOptions("\nAggie(aghast): That's a terrible name and you know it, GOO.");
			GUI.addToOptions("\nGoo: Yeah, I know. Anyway, think of a name. I'm going to see if I can find some drums.");
			GUI.addToOptions("\nAggie: TTYL");
			aggieCounter = 2;
		} else {
			GUI.clearOptions();
			GUI.addToOptions("GOO: Aggie, I've got drums. Meet me at the Jupiter Club ASAP.");
			GUI.addToOptions("\nAggie: Yeah, I'll see you there.");
			setTalkingTo(null);
		}
	}

	public static void talkToLouis(String command) {
		setTalkingTo("louis");
		GUI.clearOptions();
		if (Character.hasBigIdea() && Character.hasSaxophone()
				&& Character.hasDrums()) {
			GUI.addToOptions("You approach Louis with your band in tow. You play for him, and he, being an old fan of space jazz himself, is very pleased. You're hired on the spot, and given three laminated Jupiter Line employee lanyards.");
			Commands.takeObject("lanyards");
			Character.setHasLanyard(true);
			setTalkingTo(null);

		} else {
			GUI.clearOptions();
			GUI.addToOptions("GOO: Hey Louis, what's happenin'?");
			GUI.addToOptions("\nLouis: Nice jacket GOO. What's happening is I've got 10 dead musicians on my hands and the Jupiter Corp. is on my ass to find replacements.");
			GUI.addToOptions("\nGOO: Hey Louis, how about booking my band on the Jupiter Liner?");
			GUI.addToOptions("\n\nLouis looks from GOO to the hopeless Martian band on stage, and then back to GOO, who is trying to look professional. He gives you a wan smile.");
			GUI.addToOptions("\n\nLouis: I like you kid, but you're not ready. What are you gonna play, a saxophone reed?");

			setTalkingTo(null);

		}
	}

	public static void talkToShopkeeper(String command) {
		setTalkingTo("shopkeeper");
		shopkeeper();
	}

	public static void talkToSunRa(String command) {
		setTalkingTo("sunra");
		GUI.clearOptions();
		GUI.addToOptions("GOO: Umm, are you Sun Ra? Aren't you like 300 years old?");
		GUI.addToOptions("\n\nSun Ra: Yes, and yes. I've been expecting you. Here, take my saxophone.");
		Commands.takeObject("Take saxophone");
		Character.setHasSaxophone(true);
		GUI.addToOptions("\nGOO takes the saxophone, fits his reed to it, and gives it a blow. It sounds amazing. He can't help but fall into a fit of nervous, wondrous laughter");
		GUI.addToOptions("\nSun Ra: Ok well this deus-ex-machina is outta here. \n\nSun Ra disappears in a burst of radiance. \n\n       Sun Ra: Space is the place, GOO. \n\n            Sun Ra: Never....forget...that!");
		setTalkingTo(null);
		Commands.moveSunRaToLunarPlain();
	}

	public static void talkToCaptain(String command) {
		setTalkingTo("captain");
		captain();
	}

	public static void charlie() {
		GUI.clearOptions();
		if (charlieCounter == 1) {
			if (!Character.hasID()) {
				GUI.addToOptions("GOO: Hi Charlie. Open the gate, huh? I wanna talk to you.");
				GUI.addToOptions("\nCharlie: No can do, GOO. Only with your ID badge.");
				GUI.addToOptions("\nGOO: Ok pal. I'll see if I can dig it up.");
				charlieCounter = 0;

			}
			if (Character.hasID()) {
				GUI.addToOptions("GOO: Hey Charlie, heard anything about that Jupiter Liner?");
				GUI.addToOptions("\nCharlie: Ha, that floating lego's gonna be in dock a while longer. I guess Jupiter Liners Corp's got their people crawlin' all over the zone trying to figure out how all 12 of their musicians ended up gassin' themselves to death on Neptune Juice over in sector 3 last night. Musicians, huh?");
				GUI.addToOptions("\nGOO: Yeah, huh. Musicians. Glad I'm uhh, not one of those.");
				GUI.addToOptions("\n\nGOO begins to get an idea. But he's not about to spill it to Charlie. No need to get laughed at for the ridicul...but maybe just crazy enough to work? If he could just put a band together.");
				Character.setHasBigIdea(true);
				charlieCounter = 0;
			}
		} else {
			GUI.addToOptions("GOO: Charlie. I'm starting a band.");
			GUI.addToOptions("\nCharlie: So I hear. Good luck to you.");
		}

	}

	public static void shopkeeper() {
		GUI.clearOptions();
		if (Character.hasCreditCube()) {
			GUI.addToOptions("GOO: Hi, I'd like to buy those drums.");
			GUI.addToOptions("\nTyler: Uh sure, 50,000 credits, man.");
			Commands.dropObject("drop credit cube");
			Character.setHasCreditCube(false);
			GUI.addToOptions("\nTyler: Thank you very much. As you can see, they fold neatly into this small attache case.");
			GUI.addToOptions("\n\nTyler hands you the attache.");
			Commands.takeObject("take drumset");
		} else {
			GUI.addToOptions("GOO: Hi, I'd like to buy those drums.");
			GUI.addToOptions("\nTyler: Uh sure, 50,000 credits, man.");
			GUI.addToOptions("\nGOO: Oh, huh. Little short on credits. Maybe next time.");
			GUI.addToOptions("\nTyler: Well, they're not going anywhere.");

		}

	}

	public static void captain() {
		GUI.clearOptions();
		GUI.addToOptions("ARRRRRRR! Welcome aboard!");
	}

}
