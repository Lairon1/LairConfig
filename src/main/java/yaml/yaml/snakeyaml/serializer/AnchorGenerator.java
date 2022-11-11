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
package yaml.yaml.snakeyaml.serializer;

import yaml.yaml.snakeyaml.nodes.Node;

/**
 * Support different anchors
 */
public interface AnchorGenerator {

  /**
   * Create a custom anchor to the provided Node
   *
   * @param node - the data to anchor
   * @return value to be used in the YAML document
   */
  String nextAnchor(Node node);
}
