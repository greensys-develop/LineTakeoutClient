package jp.co.greensys.takeout.flex;

import static java.util.Arrays.asList;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Carousel;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MenuFlexMessageSupplier implements Supplier<FlexMessage> {

    @Override
    public FlexMessage get() {
        final Bubble bubble1 = createBubble(
            "チーズバーガー",
            "49.99",
            "https://2.bp.blogspot.com/-V6VfiVDMitg/WC5efOCqmFI/AAAAAAAA_5A/P97lsAxzB5kUDdJYLIz_DvdferdNRl6aACLcB/s400/food_hamburger_cheese.png",
            false
        );
        final Bubble bubble2 = createBubble(
            "テリヤキバーガー",
            "11.99",
            "https://1.bp.blogspot.com/-ccmRa-W5FdQ/WGnPWhQSnzI/AAAAAAABA4w/krKcel6z1hobC87K1Vj9bG_Me_AfBo15QCLcB/s400/hamburger_teriyaki_burger.png",
            true
        );
        final Carousel carousel = Carousel.builder().contents(asList(bubble1, bubble2)).build();
        return new FlexMessage("Catalogue", carousel);
    }

    private Bubble createBubble(String title, String price, String imageURL, Boolean isOutOfStock) {
        final Image heroBlock = createHeroBlock(imageURL);
        final Box bodyBlock = createBodyBlock(title, price, isOutOfStock);
        final Box footerBlock = createFooterBlock(isOutOfStock);
        return Bubble.builder().hero(heroBlock).body(bodyBlock).footer(footerBlock).build();
    }

    private Image createHeroBlock(String imageURL) {
        return Image
            .builder()
            .size(Image.ImageSize.FULL_WIDTH)
            .aspectRatio(Image.ImageAspectRatio.R20TO13)
            .aspectMode(Image.ImageAspectMode.Cover)
            .url(URI.create(imageURL))
            .build();
    }

    private Box createBodyBlock(String title, String price, Boolean isOutOfStock) {
        final Text titleBlock = Text.builder().text(title).wrap(true).weight(Text.TextWeight.BOLD).size(FlexFontSize.XL).build();
        final Box priceBlock = Box
            .builder()
            .layout(FlexLayout.BASELINE)
            .contents(
                asList(
                    Text
                        .builder()
                        .text("$" + price.split("\\.")[0])
                        .wrap(true)
                        .weight(Text.TextWeight.BOLD)
                        .size(FlexFontSize.XL)
                        .flex(0)
                        .build(),
                    Text
                        .builder()
                        .text("." + price.split("\\.")[1])
                        .wrap(true)
                        .weight(Text.TextWeight.BOLD)
                        .size(FlexFontSize.SM)
                        .flex(0)
                        .build()
                )
            )
            .build();
        final Text outOfStock = Text
            .builder()
            .text("Temporarily out of stock")
            .wrap(true)
            .size(FlexFontSize.XXS)
            .margin(FlexMarginSize.MD)
            .color("#FF5551")
            .build();

        FlexComponent[] flexComponents = { titleBlock, priceBlock };
        List<FlexComponent> listComponent = new ArrayList<>(Arrays.asList(flexComponents));
        if (isOutOfStock) {
            listComponent.add(outOfStock);
        }

        return Box.builder().layout(FlexLayout.VERTICAL).spacing(FlexMarginSize.SM).contents(listComponent).build();
    }

    private Box createFooterBlock(Boolean isOutOfStock) {
        final Button addToCartEnableButton = Button
            .builder()
            .style(Button.ButtonStyle.PRIMARY)
            .action(new URIAction("Add to Cart", URI.create("http://example.com"), new URIAction.AltUri(null)))
            .build();
        final Button addToCartDisableButton = Button
            .builder()
            .style(Button.ButtonStyle.PRIMARY)
            .color("#aaaaaa")
            .action(new URIAction("Add to Cart", URI.create("http://example.com"), new URIAction.AltUri(null)))
            .build();
        return Box
            .builder()
            .layout(FlexLayout.VERTICAL)
            .spacing(FlexMarginSize.SM)
            .contents(asList((!isOutOfStock) ? addToCartEnableButton : addToCartDisableButton))
            .build();
    }
}