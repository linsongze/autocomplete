package org.lsz.autocomplete.core;

import java.util.List;

/**
 * Created by lsz on 2014/11/15.
 */
public interface Suggest {
    void add(Item item);
    List<String> suggest(String word,int num);
}
