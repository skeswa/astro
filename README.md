# astro
Extraterrestrial android development.  
  
Developing good Android apps is hard, but developing _great_ Android apps feels impossible: [@nickbutcher](https://github.com/nickbutcher/plaid)'s [Plaid](https://github.com/nickbutcher/plaid) perfectly showcases how difficult it is to craft rich,interactive user experiences for Android devices. For this problem, I can identify three primary culprits:
- **Its just really tedious**  
There is a ton of boilerplate between you, the developer, and the app you want to build.
- **The SDK is huge, verbose and confusing**  
It feels as though, to use great tools and APIs, you must first pour over mountains of documentation.
- **Best practices are not obvious**  
Knowing how best to build something on Android usually requires that you've done or seen it already.  

Astro aims to simplify Android development by introducing a concise and powerful component-based architecture heavily inspired by [React](https://github.com/facebook/react). Astro encourages declarative application design, which is simpler to reason about and adapt. Astro reduces the concepts of Activities, Fragments, Views and Layouts to the singular concept of a Component. Rather than designating Java for logic and XML for presentation, Astro keeps _the whole application_ encapsulated within the compile-time checked confines of Java.

## Concept
```java
public class TestComponent2 extends Component implements ClickListener {
  private static final Field<Boolean> visible = Field.create(Boolean.class, false);

  private static final Style containerStyle = Style
    .alignItems(Flex.Alignment.CENTER)
    .flexDirection(Flex.Direction.ROW)
    .justifyContent(Flex.Justification.CENTER)
    .padding(15)
    .create();

  private static final Style itemStyle = Style
    .backgroundColor(Color.BLUE)
    .marginLeft(12)
    .create();

  private static final Style firstItemStyle = Style.from(itemStyle)
    .marginLeft(null)
    .create();

  @Override
  public void onClick() {
    Update
      .set(visible, true)
      .execute(this);
  }

  @Override
  public Element render() {
    return
      $(Container.class)
        .styles(containerStyle, valueOf(visible) ? CommonStyles.invisible : null)
        .attr(Container.name, "value")
        .attr(Container.age, 56)
        .attr(Container.onClick, this)
        .children(
          $(PersonListItem.class)
            .style(firstItemStyle)
            .attr(PersonListItem.name, "random")
            .attr(PersonListItem.age, (int) (Math.random() * 19)),
          $(PersonListItem.class)
            .style(itemStyle)
            .attr(PersonListItem.name, "random")
            .attr(PersonListItem.age, (int) (Math.random() * 19)),
          $(PersonListItem.class)
            .style(itemStyle)
            .attr(PersonListItem.name, "random")
            .attr(PersonListItem.age, (int) (Math.random() * 19)))
        .create();
  }
}
```
