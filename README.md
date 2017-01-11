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
public class Roster extends Component implements ClickListener {
  class Props {
    static final Prop<ImmutableList<Player>> players =
      Prop
        .type(ImmutableList<Player>.class)
        .defaultsTo(ImmutableList.empty())
        .optional()
        .create();
  }

  class Styles {
    static final Style container =
      Style
        .alignItems(Flex.Alignment.CENTER)
        .flexDirection(Flex.Direction.COLUMN)
        .flexGrow(1)
        .justifyContent(Flex.Justification.CENTER)
        .create();
    static final Style playerCard =
      Style
        .spacing(15)
        .flexDirection(Flex.Direction.COLUMN)
        .justifyContent(Flex.Justification.CENTER)
        .create();
    static final Style playerImage =
      Style
        .aspectRatio(2, 1)
        .flexGrow(1)
        .minWidth(200)
        .create();
  }
  
  ImmutableList<Element> getPlayerCards()

  @Override
  public Element render() {
    return
      $(Box.class)
        .styles(Styles.container)
        .children(
          $(Box.class)
            .styles(Styles.ship)
            .children(),
          $(Text.class)
            .prop(Text.Prop.content, "There 
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
