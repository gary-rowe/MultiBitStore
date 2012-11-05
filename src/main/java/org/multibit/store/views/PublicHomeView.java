package org.multibit.store.views;

import org.multibit.store.model.HomeModel;
import org.pegdown.PegDownProcessor;

import java.io.IOException;

/**
 * <p>View to provide the following to resources:</p>
 * <ul>
 * <li>Representation provided by a Freemarker template with a given model</li>
 * </ul>
 * <p>Presenting the view in this manner assists with Freemarker debugging</p>
 *
 * @since 0.0.1
 */
public class PublicHomeView extends PublicFreemarkerView<HomeModel> {

  public PublicHomeView(String templateName, HomeModel model) {
    super(templateName, model);
  }

  public String getHtml() throws IOException {

    String markdown = "## Example Markdown\n" +
      "\n" +
      "This is a paragraph\n" +
      "\n" +
      "This is another\n" +
      "\n" +
      "[This is a link](http://example.org)";
    PegDownProcessor processor = new PegDownProcessor();
    return processor.markdownToHtml(markdown);

  }
}
