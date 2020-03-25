package com.codecool.termlib;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Terminal {
    /**
     * The beginning of control sequences.
     */
    // HINT: In \033 the '0' means it's an octal number. And 33 in octal equals 0x1B in hexadecimal.
    // Now you have some info to decode that page where the control codes are explained ;)
    private static final String CONTROL_CODE = "\033[";
    /**
     * Command for whole screen clearing.
     * <p>
     * Might be partitioned if needed.
     */
    private static final String CLEAR = "2J";
    /**
     * Command for moving the cursor.
     */
    private static final String MOVE = "H";
    /**
     * Command for printing style settings.
     * <p>
     * Handles foreground color, background color, and any other
     * styles, for example color brightness, or underlines.
     */
    private static final String STYLE = "m";

    /**
     * Main method that runs the program
     * <p>
     * @param args
     */
    public static void main(String[] args) {
        LocalTime timeObject;
        DateTimeFormatter timeObjectFormat;
        String time;

        Scanner in = new Scanner(System.in);
        Boolean runApplication = true;

        Terminal terminal = new Terminal();
        setCommandMenu();

        while (runApplication) {
            timeObject = LocalTime.now();
            timeObjectFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
            time = timeObjectFormat.format(timeObject);
            System.out.print("Enter value: ");
            String command = in.nextLine();
            System.out.println("You entered: " + command);
            terminal.command(command, time);
        }
    }

    /**
     * Display menu
     * <p>
     * Display command menu to user
     */
    private static void setCommandMenu() {
        System.out.println();
        System.out.println("-------------------------------Command Menu----------------------------");
        System.out.println("~COLORS~");
        System.out.println("- fgcolor: black, red, green, yellow, blue, magenta, cyan, white");
        System.out.println("- bgcolor: black, red, green, yellow, blue, cyan, white");
        System.out.println("~MOVE CURSOR~ (with numeric attributes)");
        System.out.println("- up, down, left, right, move, char");
        System.out.println("~ATTRIBUTES~");
        System.out.println("- reset, clear, bright, dim, underline, blink, reverse, hidden, time, exit");
        System.out.println("Type commands to see this menu");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println();
    }

    /**
     * Reset printing rules in effect to terminal defaults.
     * <p>
     * Reset the color, background color, and any other style
     * (i.e.: underlined, dim, bright) to the terminal defaults.
     */
    public void resetStyle() {
        System.out.print(Attribute.RESET);
    }

    /**
     * Clear the whole screen.
     * <p>
     * Might reset cursor position.
     */
    public void clearScreen() {
        System.out.print(CONTROL_CODE + CLEAR);
    }

    /**
     * Move cursor to the given position.
     * <p>
     * Positions are counted from one.  Cursor position 1,1 is at
     * the top left corner of the screen.
     *
     * @param x Column number.
     * @param y Row number.
     */
    public void moveTo(Integer x, Integer y) {
        String setX = Integer.toString(x);
        String setY = Integer.toString(y);
        String strPosition = Direction.CURSOR_POSITION.toString();
        strPosition = strPosition.replace("{ROW}", setX);
        strPosition = strPosition.replace("{COLUMN}", setY);
        System.out.print(strPosition);
    }

    /**
     * Set the foreground printing color.
     * <p>
     * Already printed text is not affected.
     *
     * @param color The color to set.
     */
    public void setColor(Color color) {
        switch (color) {
            case BLACK:
                System.out.print(Color.BLACK);
                break;
            case RED:
                System.out.print(Color.RED);
                break;
            case GREEN:
                System.out.print(Color.GREEN);
                break;
            case YELLOW:
                System.out.print(Color.YELLOW);
                break;
            case BLUE:
                System.out.print(Color.BLUE);
                break;
            case MAGENTA:
                System.out.print(Color.MAGENTA);
                break;
            case CYAN:
                System.out.print(Color.CYAN);
                break;
            default:
                System.out.print(Color.WHITE);
        }
    }

    /**
     * Set the background printing color.
     * <p>
     * Already printed text is not affected.
     *
     * @param color The background color to set.
     */
    public void setBgColor(Color color) {
        switch (color) {
            case BG_BLACK:
                System.out.print(Color.BG_BLACK);
                break;
            case BG_RED:
                System.out.print(Color.BG_RED);
                break;
            case BG_GREEN:
                System.out.print(Color.BG_GREEN);
                break;
            case BG_YELLOW:
                System.out.print(Color.BG_YELLOW);
                break;
            case BG_BLUE:
                System.out.print(Color.BG_BLUE);
                break;
            case BG_CYAN:
                System.out.print(Color.BG_CYAN);
                break;
            default:
                System.out.print(Color.BG_WHITE);
        }
    }

    /**
     * Make printed text underlined.
     * <p>
     * On some terminals this might produce slanted text instead of
     * underlined.  Cannot be turned off without turning off colors as
     * well.
     */
    public void setUnderline() {
        System.out.print(Attribute.UNDERSCORE);
    }

    /**
     * Move the cursor relatively.
     * <p>
     * Move the cursor amount from its current position in the given
     * direction.
     *
     * @param direction Step the cursor in this direction.
     * @param amount    Step the cursor this many times.
     */
    public void setCursorPosition(Direction direction, Integer amount) {
        String amountMove = Integer.toString(amount);
        switch (direction) {
            case UP:
                String strUp = Direction.UP.toString();
                strUp = strUp.replace("n", amountMove);
                System.out.println(strUp);
                break;
            case DOWN:
                String strDown = Direction.DOWN.toString();
                strDown = strDown.replace("n", amountMove);
                System.out.println(strDown);
                break;
            case FORWARD:
                String strForward = Direction.FORWARD.toString();
                strForward = strForward.replace("n", amountMove);
                System.out.println(strForward);
                break;
            case BACKWARD:
                String strBackwward = Direction.BACKWARD.toString();
                strBackwward = strBackwward.replace("n", amountMove);
                System.out.println(strBackwward);
                break;
        }
    }

    /**
     * Set the character diplayed under the current cursor position.
     * <p>
     * The actual cursor position after calling this method is the
     * same as beforehand.  This method is useful for drawing shapes
     * (for example frame borders) with cursor movement.
     *
     * @param c the literal character to set for the current cursor
     *          position.
     */
    public void setChar(char c) {
        System.out.print(Direction.SAVE_CURSOR);
        setCursorPosition();
        System.out.print(c);
        System.out.println(Direction.RESTORE_CURSOR);
        System.out.println();

    }

    /**
     * Helper function for sending commands to the terminal.
     * <p>
     * The common parts of different commands shall be assembled here.
     * The actual printing shall be handled from this command.
     *
     * @param commandString The unique part of a command sequence.
     */
    private void command(String commandString, String time) {
        // background colors
        if (commandString.toLowerCase().contains("bgcolor")) {
            if (commandString.toLowerCase().contains("red")) {
                setBgColor(Color.BG_RED);
            } else if (commandString.toLowerCase().contains("green")) {
                setBgColor(Color.BG_GREEN);
            } else if (commandString.toLowerCase().contains("yellow")) {
                setBgColor(Color.BG_YELLOW);
            } else if (commandString.toLowerCase().contains("blue")) {
                setBgColor(Color.BG_BLUE);
            } else if (commandString.toLowerCase().contains("purple")) {
                setBgColor(Color.BG_PURPLE);
            } else if (commandString.toLowerCase().contains("cyan")) {
                setBgColor(Color.BG_CYAN);
            } else if (commandString.toLowerCase().contains("white")) {
                setBgColor(Color.BG_WHITE);
            } else if (commandString.toLowerCase().contains("black")) {
                setBgColor(Color.BG_BLACK);
            }
        }

        // foreground colors
        if (commandString.toLowerCase().contains("fgcolor")) {
            if (commandString.toLowerCase().contains("red")) {
                setColor(Color.RED);
            } else if (commandString.toLowerCase().contains("green")) {
                setColor(Color.GREEN);
            } else if (commandString.toLowerCase().contains("yellow")) {
                setColor(Color.YELLOW);
            } else if (commandString.toLowerCase().contains("blue")) {
                setColor(Color.BLUE);
            } else if (commandString.toLowerCase().contains("black")) {
                setColor(Color.BLACK);
            } else if (commandString.toLowerCase().contains("cyan")) {
                setColor(Color.CYAN);
            } else if (commandString.toLowerCase().contains("white")) {
                setColor(Color.WHITE);
            } else if (commandString.toLowerCase().contains("magenta")) {
                setColor(Color.MAGENTA);
            }
        }

        // attributes

        // underline text
        if (commandString.toLowerCase().contains("underline")) {
            setUnderline();
        }

        // reset style
        if (commandString.toLowerCase().contains("reset")) {
            resetStyle();
        }

        // text is bright
        if (commandString.toLowerCase().equals("bright")) {
            System.out.print(Attribute.BRIGHT);
        }

        // text is dim
        if (commandString.toLowerCase().contains("dim")) {
            System.out.print(Attribute.DIM);
        }

        // clear terminal
        if (commandString.toLowerCase().contains("clear")) {
            clearScreen();
        }


        if (commandString.toLowerCase().contains("reverse")) {
            System.out.print(Attribute.REVERSE);
        }

        // hide text
        if (commandString.toLowerCase().contains("hidden")) {
            System.out.print(Attribute.HIDDEN);
        }


        if (commandString.toLowerCase().contains("blink")) {
            System.out.print(Attribute.BLINK);
        }

        if (commandString.toLowerCase().equals("time")) {
            System.out.println(time);
        }

        // direction
        Scanner in = new Scanner(System.in);
        Integer amount;
        try {
            if (commandString.toLowerCase().equals("up")) {
                System.out.print("Enter the distance you want to move your cursor:");
                amount = in.nextInt();
                setCursorPosition(Direction.UP, amount);
            } else if (commandString.toLowerCase().equals("down")) {
                System.out.print("Enter the distance you want to move your cursor:");

                amount = in.nextInt();
                setCursorPosition(Direction.DOWN, amount);
            } else if (commandString.toLowerCase().equals("left")) {
                System.out.print("Enter the distance you want to move your cursor:");
                amount = in.nextInt();
                setCursorPosition(Direction.BACKWARD, amount);
            } else if (commandString.toLowerCase().equals("right")) {
                System.out.print("Enter the distance you want to move your cursor:");
                amount = in.nextInt();
                setCursorPosition(Direction.FORWARD, amount);
            }

            if (commandString.toLowerCase().equals("move")) {
                setCursorPosition();
            }

            // change a character from a position with ▒
            if (commandString.toLowerCase().equals("char")) {
                setChar(setGlyph());
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid value, please use commands to show available options.");
        }

        // show available commands
        if (commandString.toLowerCase().equals("commands")) {
            setCommandMenu();
        }

        // exit program
        if (commandString.toLowerCase().equals("exit")) {
            System.out.println("Thank you for using Terminal.");
            System.exit(1);
        }
    }

    /**
     * Set special character
     * <p>
     * Setting a special character to be printed
     * @return special char
     */
    public char setGlyph() {
        char glyph = '\u00A3';
        return glyph;
    }

    /**
     * Set cursor position
     * <p>
     * User is asked to enter two x and y position
     * The cursor is set to that position using moveTo function
     */
    public void setCursorPosition() {
        Scanner in = new Scanner(System.in);
        System.out.print("Choose x position: ");
        Integer x = in.nextInt();
        System.out.print("Choose y position: ");
        Integer y = in.nextInt();
        moveTo(x, y);
    }
}
