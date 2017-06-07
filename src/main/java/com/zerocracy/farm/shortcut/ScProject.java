/**
 * Copyright (c) 2016-2017 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zerocracy.farm.shortcut;

import com.zerocracy.jstk.Farm;
import com.zerocracy.jstk.Item;
import com.zerocracy.jstk.Project;
import com.zerocracy.pmo.Pmo;
import java.io.IOException;
import lombok.EqualsAndHashCode;

/**
 * Project that understands shortcuts.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.11
 */
@EqualsAndHashCode(of = "origin")
final class ScProject implements Project {

    /**
     * Origin project.
     */
    private final Project origin;

    /**
     * Farm.
     */
    private final Farm farm;

    /**
     * Ctor.
     * @param pkt Project
     * @param frm Farm
     */
    ScProject(final Project pkt, final Farm frm) {
        this.origin = pkt;
        this.farm = frm;
    }

    @Override
    public String toString() {
        return this.origin.toString();
    }

    @Override
    public Item acq(final String file) throws IOException {
        final Item item;
        if (file.startsWith("../")) {
            item = new Pmo(this.farm).acq(file.substring(3));
        } else {
            item = this.origin.acq(file);
        }
        return item;
    }

}