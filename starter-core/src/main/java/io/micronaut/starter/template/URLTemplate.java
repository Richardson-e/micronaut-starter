/*
 * Copyright 2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.starter.template;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class URLTemplate implements Template {

    private final String path;
    private final URL url;
    private final boolean executable;

    public URLTemplate(String path, URL url) {
        this(path, url, false);
    }

    public URLTemplate(String path, URL url, boolean executable) {
        this.path = path;
        this.url = url;
        this.executable = executable;
    }

    @Override
    public void write(OutputStream outputStream) throws IOException {
        try (InputStream inputStream = url.openStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        }
    }

    public boolean isExecutable() {
        return executable;
    }

    @Override
    public String getPath() {
        return path;
    }
}