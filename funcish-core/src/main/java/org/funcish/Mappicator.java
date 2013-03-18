package org.funcish;

import java.util.Collection;

public interface Mappicator<K, V> extends Mapping<K, V>, Applicator<K, Collection<V>, V> {

}
