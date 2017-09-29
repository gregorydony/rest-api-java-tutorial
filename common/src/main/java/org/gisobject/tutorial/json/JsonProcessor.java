package org.gisobject.tutorial.json;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by GIS Object on 22/09/2017.
 */
public interface JsonProcessor<E> {

    List<E> readJson(InputStream is);

    void writeJson(List<E> input, OutputStream os);
}
