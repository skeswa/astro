# astro
Extraterrestrial android development.  
  
Developing good Android apps is hard, but developing _great_ Android apps feels impossible: [@nickbutcher](https://github.com/nickbutcher/plaid)'s [Plaid](https://github.com/nickbutcher/plaid) perfectly showcases how difficult it is to craft rich, interactive user experiences for Android. For this problem, I can identify three primary culprits:
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
  private static class Styles {
    static final Style container =
      Style
        .alignItems(Flex.Alignment.CENTER)
        .flexDirection(Flex.Direction.ROW)
        .justifyContent(Flex.Justification.CENTER)
        .padding(15)
        .create();
    static final Style item =
      Style
        .backgroundColor(Color.BLUE)
        .marginLeft(12)
        .create();
    static final Style firstItem =
      Style
        .extends(itemStyle)
        .marginLeft(null)
        .create();
  }

  private static class Fields {
    static final Field<Boolean> visible =
      Field
        .type(Boolean.class)
        .defaultsTo(false)
        .create();
  }

  public static class Props {
    public static final Prop<Boolean> visible =
      Prop
        .type(Boolean.class)
        .defaultsTo(false)
        .create();
  }

  @Override
  public void onClick() {
    Update
      .set(Fields.visible, true)
      .execute(this);
  }

  @Override
  public Element render() {
    return
      $(Container.class)
        .styles(Style.container, valueOf(Fields.visible) ? CommonStyles.invisible : null)
        .prop(Container.Props.name, "value")
        .prop(Container.Props.age, 56)
        .prop(Container.Props.onClick, this)
        .children(
          $(PersonListItem.class)
            .style(Styles.firstItem)
            .prop(PersonListItem.Props.name, "random")
            .prop(PersonListItem.Props.age, (int) (Math.random() * 19)),
          $(PersonListItem.class)
            .style(Styles.firstItem)
            .prop(PersonListItem.Props.name, "random")
            .prop(PersonListItem.Props.age, (int) (Math.random() * 19)),
          $(PersonListItem.class)
            .style(Styles.firstItem)
            .prop(PersonListItem.Props.name, "random")
            .prop(PersonListItem.Props.age, (int) (Math.random() * 19)))
        .create();
  }
}
```

#### Update: Sampleapp successfully renders!  
![Imgur](http://i.imgur.com/Jt0vx6e.png)
