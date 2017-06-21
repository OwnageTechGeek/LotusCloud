package org.lotuscloud.master.web;

import java.util.HashMap;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public abstract class WebHandler {
    public abstract String process(HashMap<String, String> request);
}