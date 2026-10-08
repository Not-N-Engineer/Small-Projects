import time

#ANSI codes
BOLD = '\033[1m'
ITALICS = '\033[3m'
END = '\033[0m'

#VARIABLES
#Game state variables
unlocked_actions = ["observe"]
inventory = []
game_state = "intro"  # starting point
current_room = 1
current_orientation = 0
temp_timer = 1

#Interaction variables
interactions = {
    1: {  # Room 1
        0: {  # Orientation 0
            "text": ["You push yourself out into the rain. ", "You close your eyes... ", "You crawl onto the table... ", "You look out of the window longingly. Are you sure about this? ", "You reach your arm over the desk and feel the leather of your father's Bible. \nTo the left of it, some rosaries. To the right, a cold metal object. \nYou take all three."],
            "items": [4, ["Bible", "Rosaries", "Metal Key"]],
            "times_done": 5, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": True,
            "special_interaction_time": 0,
            "dialogue": False
        },
        1: {
            "text": ["You open the cabinets and find nothing inside but dust. "],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False,  
            "special_interaction": False,
            "dialogue": False
        },
        2: {
            "text": ["You notice a small scrap of paper next to the clock's base. \nIt is a picture of someone you do not recognize. ", "You try to open the clock and find that it is locked"],
            "items": [0, ["Picture"]],
            "times_done": 2, 
            "done": False, 
            "can_use_item": True, 
            "usable_items": ["metal key"],
            "metal key_text": "The key clicks into the keyhole in the clock. \nYou open the clock and take the weights out of the back.",
            "metal key_items": ["Brass Clock Weights"],
            "special_interaction": True,
            "special_interaction_time": 1,
            "dialogue": False
        },
        3: {
            "text": ["The door is locked"],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": False,
            "dialogue": False
        }
    },
    
    2: {  #Outside window
        0: {  # Orientation 0
            "text": ["You see a man in front of you. You talk to him: "],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": False,
            "dialogue": True,
            "dialogue_id": "first_meeting"
        },
        1: {
            "text": ["You see a crowd of people around you. "],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": False,
            "dialogue": False
        },
        2: {
            "text": ["You see a crowd of people around you. "],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": False,
            "dialogue": False
        },
        3: {
            "text": ["You see a crowd of people around you. "],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": False,
            "dialogue": False
        }
    }, 
    
    3: {  #Central Street
        0: {  # Orientation 0
            "text": ["You see a man behind the barricade. You talk to him: "],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": False,
            "dialogue": True,
            "dialogue_id": "barricade_guard"
        },
        1: {
            "text": ["You hear gunfire and screaming in this direction. \nYou decide not to go down this road. "],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": False,
            "dialogue": False
        },
        2: {
            "text": ["This is where you just came from. "],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": False,
            "dialogue": False
        },
        3: {
            "text": ["You see a crowd of people around you. "],
            "items": [],
            "times_done": 1, 
            "done": False, 
            "can_use_item": False, 
            "special_interaction": False,
            "dialogue": False
        }
    }
}

#Observation variables
rooms = { 
    1: [
    """
     ________________________________________
    |  ____________________________________  |
    | |     /_/ /_/ /_/ /_/ /_/ /_/ /_/ /_/| |
    | |    / /_/ /_/ /_/ /_/ /_/ /_/ /_/ /_| |
    | |   /_/ /_/ /_/ /_/ /_/ /_/ /_/ /_/ /| |
    | |  / /_/ /_/ /_/ /_/ /_/ /_/ /_/ /_/ | |
    | | /_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/| |
    | |  |       /  ___________            | |
    | |  | /       |     | /   |   /       | |
    | |  |    /    |-----|-----|        /  | |
    | |  |       / |/____|_____|           | |
    | |  |   /                  /     /    | |
    | |  |        /      /  /           /  | |
    | |  |  /           /         /        | |
    | |__|_UU_______/________/_________/___| |
    |_____oO|oU______________________________|
         (     ) 
          \   /     _______
  _________) (..._ |_______ _()_________________
 |______________________________________________|
    | | ||                              || | |
    | | ||                              || | |
    | | ||                              || | |
    | | ||                              || | |
    | | ||                              || | |
    | | ||                              || | |
    | | ||                              || | |
    | | ||                              || | |
    |_| ||                              || |_|
    """,
    """
     _______________________________________
    |  ___________________________________  |
    | |        |        |        |        | |
    | |        |        |        |        | |
    | |        |        |        |        | |
    | |       O|       O|       O|       O| |
    | |        |        |        |        | |
    | |________|________|________|________| |
    |_______________________________________|
                                _    _____
                               | |_  .....
                              /| |_|(     ) 
    ______                   | | |_| \   / 
   ||_____|             ____/_/|_|_|__) (_()____
   ||     |            |________________________|
   ||_____|
   ||_____|
   ||     |
   ||_____|
   ||_____|
   ||     |
   ||_____|___________
   |__________________|
    | | ||      | | ||
    | | ||      | | ||
    | | ||      | | ||
    | | ||      | | ||                    _____
    | | ||      | | ||                   |.__. |
    |_| ||      |_| ||                   |.  . |
    """,
    """ 
                  ______
              ___/(++++)\___
            ./ ____________ \.
            | |   ______   | |
            | |  /      \  | |
            | | /    ___ \ | |
            | | \    \   / | |
            | |  \____\_/  | |
            | |____________| |
            ./ ____________ \.
            | |     ||     | |
            | |     ||     | |
            | |     ||     | |
            | |     ||     | |
            | |     ||     | |
            | |     ||     | |
            |∆|     ||     | |
            | |     ||     | |
            | |     ||     | |
            | |     ||     | |
            | |    _||_    | |
            | |   /    \   | |
            | |  |      |  | |
            | |   \____/   | |
            | |____________| |
            |________________|
          ./__+-+___--___+-+__\.
          |                    |/|_
          |____________________|??\;
    """,
    """
     ________________________________________
    |  ____________________________________  |
    | |\__/_|_          ||                 | |
    | |/_|_/            ||                 | |
    | |/ | \            ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |            ____ || ____            | |
    | |               \O||O/               | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    | |                 ||                 | |
    |_|_________________||_________________|_|
    """,
    ], 

    2: [
    """
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
///////////////////////;;'':;;:';;///////////////
/////////////////////;:     ::     :;////////////
/////////////////;;;:::  |\    /|  ::////////////
//////////;;:::::     ___| \,,/_/  :;////////////
//////:::::   _____---__/ \/    \   :;///////////
////;:   ____/ / |__--/     (D)  \   :;//////////
////;:  |  _|....._ -/    (_      \   :;/////////
/////;  | | | |  // /       \_ /  -\   :;////////
////;  /| | | | /           / \_ o o)  :;////////
///;  / | | | ||           /    \__/     ;;//////
:::  / /| |_|..|          /    ..=(((=..  :;/////
  __/_/_|____\_ \      \_/\____|/-   -\|   :;////
 |_____| |______|_      /  |__(. o   o .)  :;////
    | ||_|      ||\    /\  :| |!  .U.  !  :;/////
    | |         | ||  |   \ \ | \(/-\)/  :;//////
    | |          || | |     \ ) !`---'!   ;;/////
    | |          || | )     | |/'     `\     ;;:;
    | |          || | |   _.-;' ._\ /_. `:-._    
    | | ᘛ⁐̤ᕐᐷ    /_\ | | /  \ \    `.'    / /  \.
    |_|             <__\|   |_|          | |   |
                        |   | |          | |   |
    """,
    """
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
    """,
    """ 
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
    """,
    """
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
    """,
    ], 

    3: [
    """
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////;:''''''''''':;/////////////////////
:::::::::;//;:   .=+++=.   :;////////////////////
 _______  :;:   |/     \|   :;///////////////////
/ / | \ \      (| o` 'o |)  :;:::::::::::::://///
.........|      |   U   | ________________  :;///
 |  |  | |______|_  -   ||  ____________  | :;///
 |  |  | |______  |____/ | |++++++++++++| | :;///
 |  |  | |\____ | |   |  | |++++++++++++| | :;///
.........|_____\| |\_/ \_| |++++++++++++| | :;:::
\_\_|_/_/_________|______| |++++++++++++|_|____
 | |/ / |  |  __________  ||++++|  __________  |
 | / / /|  | |__________|_||++++| |__________| |
 |/ / /||  | |_________/ / | \ \| |__________| |
 / / / ||  | |_______ |.........| |__________| |
/ / /| ||__|_/ / | \ \| |  |  | |_|  _   _   _ |_
 / / | ||  _|.........| |  |  | | |_| |_| |_| |_|
/_/|_|_|| | | |  |  | |_|__|__|_| |_| |_| |_| |_|
 ______/| | | |  |  | |______  || |_| |_| |_| |_|
| | | / | | | |  |  | |\____ | || |_| |_| |_| |_|
| | |/ /| |_|.........|_____\| || | | | | | | | |
| |_/_/_|____\_\_|_/_/_________|| | | | | | | | |
    """,
    """
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
    """,
    """ 
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
    """,
    """
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
    """,
    ], 
}

#Dialogue variables
dialogues = {
    "first_meeting": {
        "start": {
            "text": "The man looks down at you. 'Are you hurt?'",
            "choices": {
                "I'm fine. Who are you?": "ask_identity",
                "Where am I?": "ask_location",
                "Stay silent.": "silent"
            }
        },
        "ask_identity": {
            "text": "'My name is Jakob. I saw you fall from the window.'",
            "choices": {
                "Why did you help me?": "why_help",
                "Stay silent.": "silent"
            }
        },
        "ask_location": {
            "text": "'You're in the center of Berlin. This place is not safe for you.'",
            "choices": {
                "Why is it unsafe?": "unsafe",
                "Who are you?": "ask_identity"
            }
        },
        "why_help": {
            "text": "'Because you're going to die if I don't. The soldiers are marching into the city quickly, we need to get out. '",
            "choices": {
                "What do you mean?": "truth_reveal",
                "I don't believe you.": "distrust"
            }
        },
        "unsafe": {
            "text": "'The soldiers are searching every home. We should leave quickly.'",
            "choices": {
                "Leave with Jakob.": "leave_with",
                "Refuse to go.": "refuse"
            }
        },
        "silent": {
            "text": "He sighs. 'If you won't speak, then follow me. It's safer.'",
            "choices": {
                "Follow him.": "leave_with",
                "Stay put.": "refuse"
            }
        },
        "truth_reveal": {
            "text": "Jakob leans closer: 'They're shooting everyone they find, they won't hesistate to kill you.' ",
            "choices": {
                "Leave with Jakob.": "leave_with",
                "Stay put.": "refuse"
            }
        },
        "distrust": {
            "text": "Jakob frowns: 'Suit yourself.' He gets back on his carriage and begins to ride away.",
            "choices": {
                "Follow him anyway.": "leave_with",
                "Stay put.": "refuse"
            }
        },
        "leave_with": {
            "text": "You decide to follow Jakob out of the city.",
            "end": True,
            "consequences": {
                "game_state": "outside_berlin",
                "current_room": 30
            }
        },
        "refuse": {
            "text": "You decide to head the other way. \nSoon, crawling in the rain beneeth the feet of the running crowd, you reach a barricaded point in the central street",
            "end": True,
            "consequences": {
                "game_state": "central_street",
                "current_room": 3
            }
        }
    },
    
    #IF YOU DON'T LEAVE WITH JAKOB
    "barricade_guard": {
        "start": {
            "text": "'Revolutionaries only beyond this point,' he growls.",
            "choices": {
                "I'm with you. I want freedom too.": "rebel_trust",
                "I'm just trying to get home.": "civilian_path",
                "Stay silent.": "silent"
            }
        },
        "rebel_trust": {
            "text": "'You? You don't look like a fighter.' He narrows his eyes.",
            "choices": {
                "I can't fight, but I can help.": "rebel_accept",
                "You're right. Let me through anyway.": "rebel_refuse"
            }
        },
        "civilian_path": {
            "text": "'Then go around. The soldiers are shooting anyone on this street.'",
            "choices": {
                "Beg him to let you pass.": "rebel_pity",
                "Turn back.": "turn_back"
            }
        },
        "silent": {
            "text": "The man spits on the ground. 'Suit yourself. Stay out of the way, cripple.'",
            "end": True,
            "consequences": {
                "game_state": "side_street",
                "current_room": 5
            }
        },
        "rebel_accept": {
            "text": "'...Fine. You can pass. But don't slow us down.'",
            "end": True,
            "consequences": {
                "game_state": "behind_barricade",
                "current_room": 6
            }
        },
        "rebel_refuse": {
            "text": "'Then get lost.' He pushes you back.",
            "end": True,
            "consequences": {
                "game_state": "side_street",
                "current_room": 5
            }
        },
        "rebel_pity": {
            "text": "He hesitates, then sighs. 'Alright. Go on, but stay out of sight.'",
            "end": True,
            "consequences": {
                "game_state": "behind_barricade",
                "current_room": 6
            }
        },
        "turn_back": {
            "text": "You quickly turn around and crawl into a nearby alleyway, heart pounding. The streets behind you echo with gunfire.",
            "end": True,
            "consequences": {
                "game_state": "alley_escape",
                "current_room": 7
            }
        }
    }
}


#FUNCTIONS
#Prompt the user until valid response
def prompt(message, valid_inputs):
    while True:
        cont = input(message)
        if cont in valid_inputs:
            return cont
        else:
            print("Try again. ")

#Prompt the user to do some actions
def prompt_actions(actions):
    print("\nActions: " + ", ".join(actions))
    while True:
        cont = input("What do you want to do? ").lower()
        if cont in actions:
            return cont
        elif cont == "skip":
            return cont
        else:
            print("Try again. ")

#Perform actions
def perform_actions(action):
    if action == "turn left" or action == "turn right":
        turn(action)
        print_surroundings()
    elif action == "observe":
        print_surroundings()
    elif action == "interact":
        print_interaction()
    elif action == "inventory":
        print_inventory()
    elif action == "use item":
        use_item()

#Print the user's surroundings
def print_surroundings():
    print(rooms[current_room][current_orientation])
    time.sleep(1)
    prompt(f"\nPress {BOLD}'e'{END} to continue... ", ["e"])

#Turning the user
def turn(turn_direction):
    global current_orientation
    if turn_direction == "turn left":
        current_orientation = (current_orientation + 1) % 4
        print("Turned left")
    elif turn_direction == "turn right":
        current_orientation = (current_orientation - 1) % 4
        print("Turned right")

#Interacting with the environment
def print_interaction():
    global game_state

    room_data = interactions.get(current_room, {})
    interaction = room_data.get(current_orientation, None)

    if interaction is None:
        print("\nThere's nothing to interact with here.")
        return

    if not interaction["done"]:
        interaction["times_done"] -= 1
        index = interaction["times_done"]
        print("\n" + interaction["text"][index])
        if interaction["items"] and index == interaction["items"][0]:
            inventory.extend(interaction["items"][1])
            print("Items gained:")
            for item in interaction["items"][1]:
                print(f"- {item}")
        if interaction["special_interaction"]:
            if interaction["times_done"] == interaction["special_interaction_time"]:
                special_interaction_check()
                interaction["done"] == True
        if interaction["dialogue"]:
            start_dialogue(interaction["dialogue_id"])
    else:
        print("\nYou've already checked here. Nothing new.")

#Check if the interaction is special
def special_interaction_check():
    global current_room
    global current_orientation
    global game_state
    if current_room == 1 and current_orientation == 0:
        game_state = "outside_window"
        current_room = 2
        current_orientation = 0
        print(f"{ITALICS}'Hey...'{END}")
        time.sleep(1)
        print(f"{ITALICS}'Hello?'{END}")
        time.sleep(1)
        print(f"{ITALICS}'Are you okay?'{END}")
        time.sleep(1)
        print("You wake up.")
        time.sleep(1)
        print("As you open your eyes, you see a man standing over you. ")
        print("The rain is intense. ")
    elif current_room == 1 and current_orientation == 2:
        print(f"New action unlocked!: {BOLD}'use item'{END}")
        unlocked_actions.append("use item")

#Print the user's inventory
def print_inventory():
    print("\nYou check your bag:")
    for item in inventory:
        print(f"- {item}")

#Allow the user to use items
def use_item():
    global game_state
    global inventory

    room_data = interactions.get(current_room, {})
    interaction = room_data.get(current_orientation, None)
    
    print_inventory()
    item_used = prompt("Which item do you use?: ", inventory).lower()
    
    if interaction["can_use_item"]:
        if item_used in interaction["usable_items"]:
            print("\n" + interaction[f"{item_used}_text"])
            if interaction[f"{item_used}_items"]:
                inventory.extend(interaction[f"{item_used}_items"])
                print("Items gained:")
                for item in interaction[f"{item_used}_items"]:
                    print(f"- {item}")
        else:
            print("Item cannot be used here. ")
    else:
        print("Item cannot be used here. ")

#Enter dialogue
def start_dialogue(dialogue_id):
    global game_state, current_room, current_orientation
    
    dialogue = dialogues[dialogue_id]
    node = "start"

    while True:
        entry = dialogue[node]
        print("\n" + entry["text"] + "\n")
        time.sleep(1)

        # If dialogue ends here
        if "end" in entry and entry["end"]:
            if "consequences" in entry:
                for key, value in entry["consequences"].items():
                    globals()[key] = value  # update global variables like game_state
            break

        # Show choices
        choices = list(entry["choices"].keys())
        for i, choice in enumerate(choices, 1):
            print(f"{i}. {choice}")

        # Get player choice
        while True:
            try:
                sel = int(input("Choose: "))
                if 1 <= sel <= len(choices):
                    node = entry["choices"][choices[sel-1]]
                    break
                else:
                    print("Invalid choice.")
            except ValueError:
                print("Enter a number.")
    


# GAME LOOP
running = True
while running:
    if game_state == "intro":
        print("You know exactly who you are and where you want to go. \nBut you are disabled...\nso you can't go anywhere. ")
        time.sleep(5)
        print("haha")
        time.sleep(1)
        prompt(f"\nPress {BOLD}'e'{END} to begin... ", ["e"])
        print(f"\n\n {BOLD}--- Chapter 1 ---{END} ")
        print("Hello, Friedrich Müller")
        game_state = "tutorial"

    elif game_state == "tutorial":
        action = prompt_actions(unlocked_actions)
        if action == "observe":
            print_surroundings()
            print(f"New action unlocked!: {BOLD}'interact'{END}")
            unlocked_actions.append("interact")
            game_state = "chapter1_interact"
        elif action == "skip":
            print("Tutorial skipped.")
            game_state = "chapter1"
            inventory.extend(["Bible", "Rosaries", "Metal Key"])
            unlocked_actions.extend(["interact", "inventory", "turn left", "turn right"])
            temp_timer = 10
            room_data = interactions.get(current_room, {})
            interaction = room_data.get(current_orientation, None)
            interaction["times_done"] = 4

    elif game_state == "chapter1_interact":
        action = prompt_actions(unlocked_actions)
        if action == "interact":
            print_interaction()
            print(f"New action unlocked!: {BOLD}'inventory'{END}")
            unlocked_actions.append("inventory")
            game_state = "chapter1_inventory"
        elif action == "observe":
            print_surroundings()

    elif game_state == "chapter1_inventory":
        action = prompt_actions(unlocked_actions)
        if action == "inventory":
            print_inventory()
            print(f"New actions unlocked!: {BOLD}'turn left'{END}, {BOLD}'turn right'{END}")
            unlocked_actions.extend(["turn left", "turn right"])
            game_state = "chapter1_turning"
        elif action == "observe":
            print_surroundings()
        elif action == "interact":
            print("Not yet...")
    
    elif game_state == "chapter1_turning":
        action = prompt_actions(unlocked_actions)
        if action == "turn left" or action == "turn right":
            turn(action)
            print_surroundings()
            print("Congratulations! You've reached the end of the tutorial")
            print("You should probably move quickly though, you don't want to be here when you father returns. ")
            temp_timer = 10
            game_state = "chapter1"
        elif action == "observe":
            print_surroundings()
        elif action == "interact":
            print("Not yet...")
        elif action == "inventory":
            print_inventory()
    
    elif game_state == "chapter1":
        action = prompt_actions(unlocked_actions)
        perform_actions(action)
        if temp_timer == 0 and game_state == "chapter1":
            temp_timer = 1
            time.sleep(1)
            print("Oh no... You hear the door handle turn, and the door creaks open.")
            time.sleep(1)
            print("You quickly crawl underneath the table and hide. ")
            time.sleep(1)
            print("...")
            time.sleep(1)
            print("It was not a very good hiding place.")
            time.sleep(1)
            print("Your father drags you out from underneath the table. ")
            time.sleep(1)
            print("You've been caught. ")
            time.sleep(1)
            print("Game over...")
        else:
            temp_timer -= 1

    else:
        action = prompt_actions(unlocked_actions)
        perform_actions(action)