/**
 * Copyright (c) 2008, SnakeYAML
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package yaml.yaml.snakeyaml.representer;

import yaml.yaml.snakeyaml.nodes.Node;

/**
 * Create a Node Graph out of the provided Native Data Structure (Java instance).
 *
 * @see <a href="http://yaml.org/spec/1.1/#id859109">Chapter 3. Processing YAML Information</a>
 */
public interface Represent {

  /**
   * Create a Node
   *
   * @param data the instance to represent
   * @return Node to dump
   */
  Node representData(Object data);
}
