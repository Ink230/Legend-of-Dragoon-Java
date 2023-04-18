package legend.game.characters;

import legend.core.GameEngine;
import legend.game.modding.registries.RegistryId;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ElementSet implements Iterable<Element> {
  private final Set<Element> elements = new HashSet<>();

  public void set(final ElementSet other) {
    this.clear();

    for(final Element element : other) {
      this.add(element);
    }
  }

  public void add(final Element element) {
    this.elements.add(element);
  }

  public void remove(final Element element) {
    this.elements.remove(element);
  }

  public void clear() {
    this.elements.clear();
  }

  public boolean contains(final Element element) {
    return this.elements.contains(element);
  }

  public int size() {
    return this.elements.size();
  }

  public boolean isEmpty() {
    return this.elements.isEmpty();
  }

  /**
   * TODO remove this
   */
  @Deprecated
  public ElementSet unpack(final int packed) {
    this.clear();

    for(final RegistryId elementId : GameEngine.REGISTRIES.elements) {
      final Element element = GameEngine.REGISTRIES.elements.getEntry(elementId).get();

      if((packed & element.flag) != 0) {
        this.add(element);
      }
    }

    return this;
  }

  /**
   * TODO remove this
   */
  @Deprecated
  public int pack() {
    int packed = 0;

    for(final Element element : this) {
      packed |= element.flag;
    }

    return packed;
  }

  @Override
  public Iterator<Element> iterator() {
    return this.elements.iterator();
  }
}
