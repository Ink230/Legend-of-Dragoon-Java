package legend.game.input;

import legend.core.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static legend.game.unpacker.Unpacker.LOGGER;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_LAST;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickGUID;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickName;
import static org.lwjgl.glfw.GLFW.glfwJoystickPresent;

public final class InputControllerAssigner {
  private final static int desiredControllerCount = 1;

  private static final List<InputControllerData> assignedControllers = new ArrayList<>();
  private static final List<InputControllerData> connectedControllers = new ArrayList<>();
  private static final List<InputControllerData> idleControllers = new ArrayList<>();

  private static boolean isAssigningControllersBool;

  public static void init() {
    logConnectedControllers();

    ControllerDatabase.loadControllerDB();
    boolean noControllerFound = true;
    final String controllerGuidFromConfig = Config.controllerGuid();

    for(int i = 0; i < GLFW_JOYSTICK_LAST; i++) {
      if(controllerGuidFromConfig.equals(glfwGetJoystickGUID(i))) {
        final InputControllerData controllerData = new InputControllerData(glfwGetJoystickName(i), glfwGetJoystickGUID(i), i);
        controllerData.setPlayerSlot(1);
        connectedControllers.add(controllerData);
        assignedControllers.add(controllerData);
        Input.refreshControllers();
        noControllerFound = false;
        LOGGER.info("Last used controller found and connected");
        break;
      }
    }

    if(noControllerFound) {
      reassignSequence();
    }
  }

  private static void logConnectedControllers() {
    for(int i = 0; i < GLFW_JOYSTICK_LAST; i++) {
      if(glfwJoystickPresent(i)) {
        LOGGER.info("Controller Id {} - GUID: {}", i, glfwGetJoystickGUID(i));
      }
    }
  }

  public static void reassignSequence() {
    grabAllPossibleControllers();
  }

  public static void grabAllPossibleControllers() {
    connectedControllers.clear();
    idleControllers.clear();
    assignedControllers.clear();

    for(int i = 0; i < GLFW_JOYSTICK_LAST; i++) {
      if(glfwJoystickPresent(i)) {
        System.out.println((i + 1) + ": " + glfwGetJoystickName(i) + " (" + glfwGetJoystickName(i) + ')');
        InputControllerData controllerData = new InputControllerData(glfwGetJoystickName(i), glfwGetJoystickGUID(i), i);
        connectedControllers.add(controllerData);
      }
    }
    LOGGER.info("Found a total of {}", connectedControllers.size());

    if(connectedControllers.isEmpty()) {
      LOGGER.info("No controllers connected");
    }
    if(connectedControllers.size() == 1) {

      LOGGER.info("Only 1 controller connected so assigning by default. {}", connectedControllers.get(0).getInfoString());
      connectedControllers.get(0).setPlayerSlot(1);
      assignedControllers.add(connectedControllers.get(0));
      savePlayerOneToConfig();
    }
    if(connectedControllers.size() >= 2) {
      LOGGER.info("Multiple controllers connected. Please press any of the buttons for the one you want to use");
      isAssigningControllersBool = true;
      idleControllers.addAll(connectedControllers);
    }
  }

  public static void update() {
    for(final InputControllerData controllerData : idleControllers) {
      controllerData.updateState();
      if(controllerData.hasAnyButtonActivity()) {
        LOGGER.info("Button motion detected with controller {}", controllerData.getInfoString());
        assignedControllers.add(controllerData);
        idleControllers.remove(controllerData);

        LOGGER.info("Assigning as player {}", assignedControllers.size());
        controllerData.setPlayerSlot(assignedControllers.size());
        if(assignedControllers.size() == 1) {
          savePlayerOneToConfig();
        }

        if(assignedControllers.size() < desiredControllerCount) {
          LOGGER.info("Please press the buttons for player {}", assignedControllers.size() + 1);
        }

        break;
      }
    }

    if(assignedControllers.size() >= desiredControllerCount) {
      LOGGER.info("Target controller count of " + desiredControllerCount + " reached");
      isAssigningControllersBool = false;
      Input.refreshControllers();
    }

    if(idleControllers.isEmpty()) {
      LOGGER.info("No more controllers detected. Continuing with the {} assigned controllers", assignedControllers.size());
      isAssigningControllersBool = false;
      Input.refreshControllers();
    }

  }

  public static boolean isAssigningControllers() {
    return isAssigningControllersBool;
  }

  public static List<InputControllerData> getAssignedControllers() {
    return assignedControllers;
  }

  public static List<InputControllerData> getConnectedControllers() {
    return connectedControllers;
  }

  public static List<InputControllerData> getIdleControllers() {
    return idleControllers;
  }

  private static void savePlayerOneToConfig() {
    Config.controllerGuid(assignedControllers.get(0).getGlfwJoystickGUID());

    try {
      Config.save();
    } catch(final IOException e) {
      System.err.println("Failed to save config");
    }

  }

}
