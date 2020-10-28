package com.devx.angular.shop.services;

import com.devx.angular.shop.dto.BeerDetailDto;
import com.devx.angular.shop.dto.BeerDto;
import com.devx.angular.shop.errorhandling.NoSuchBeerException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerService {
	private static final String IMAGES_PATH = "/images/";
	List<BeerDetailDto> beers = new ArrayList<>();


	public BeerService() {
		beers.add(createBear("American Beauty", "AmericanBeauty_189x735.png", AMERICAN_BEAUTY, "25", "5%", List.of(
				"Cascade PL", "Mosaic"), "American Blonde Ale"));
		beers.add(createBear("Califia", "Califia_189x735.png", CALIFA, "70", "7%", List.of("Columbus", "Centennial",
				"Cascade", "Citra"), "West Coast IPA"));
		beers.add(createBear("Idiota", "Idiota_167x735.png", IDIOTA, "70", "10,5%", List.of("Cascade"), "Russian " +
				"Imperial Stout"));
		beers.add(createBear("Misty", "Misty_189x735.png", MISTY, "40", "5,5%", List.of("Mosaic", "Citra", "Amarillo",
				"Columbus", "Chinook", "alisade"), "Contemporary IPA"));
		beers.add(createBear("Pan IPani", "Pani_IPani_189x735.png", PAN_I_PANI, "45", "6%", List.of("Perle", "Amarillo",
				"Citra", "Mosaic"), "Wheat IPA"));
		beers.add(createBear("Pia", "Pia_189x735.png", PIA, "25", "4,4%", List.of("Wai-iti", "Motoueka", "Galaxy"),
				"NZ Session IPA"));
		beers.add(createBear("Saisonator", "Saisonator_167x735.png", SAISONATOR, "70", "12,2%", List.of("Columbus",
				"Palisade", "Cascade US", "Cascade PL", "Equinox", "Centennial"), "Imperial Saison"));
		beers.add(createBear("Taura", "Taura_189x735.png", TAURA, "40", "6.6%", List.of("Wai-iti", "Mouteka",
				"Galaxy"), "NZ Double IPA"));
		beers.add(createBear("Tohunga", "Tohunga_189x735.png", TOHUNGA, "50", "8,3%", List.of("Motueka",
				"Nelson Sauvin", "Rakau", "Wai-iti", "Mosaic", "Iunga"), "NZ Triple IPA"));
	}

	public List<BeerDto> getAllBears() {
		return beers.stream().map(this::toSimpleBeer).collect(Collectors.toList());
	}

	private BeerDetailDto createBear(String name, String img, String desc, final String ibu, final String alc,
									 final List<String> hops, final String style) {
		var beer = new BeerDetailDto();
		beer.setName(name);
		beer.setImg(IMAGES_PATH + img);
		beer.setDescription(desc);
		beer.setIbu(ibu);
		beer.setAlc(alc);
		beer.setHops(hops);
		beer.setStyle(style);
		return beer;
	}

	public BeerDetailDto getBeer(final String name) {
		return beers.stream().filter(b -> b.getName().equals(name)).findFirst().orElseThrow(() -> new NoSuchBeerException("No such beer"));
	}

	private BeerDto toSimpleBeer(BeerDetailDto beerDetailDto) {
		var beer = new BeerDto();
		beer.setName(beerDetailDto.getName());
		beer.setImg(beerDetailDto.getImg());
		beer.setStyle(beerDetailDto.getStyle());
		beer.setIbu(beerDetailDto.getIbu());
		beer.setAlc(beerDetailDto.getAlc());
		return beer;
	}

	String PAN_I_PANI = "Pan IPAni to doskonałe piwo na początek przygody ze stylem India Pale Ale oraz na pierwsze " +
			"zetknięcie się ze zniewalająco aromatycznymi amerykańskimi chmielami. " +
			"Jest hybrydą dwóch piwnych światów. Lekkość i zwiewność kojarzą się z piwem pszenicznym, zaś alkoholowa" +
			" moc, goryczka i aromat pochodzą od stylu India Pale Ale. " +
			"W aromacie dominują akcenty cytrusów, gruszek i mango przeplatane delikatnymi nutami kwiatowymi. " +
			"Jasnozłote, lekko zamglone. W smaku cytrusowe, treściwe i umiarkowanie goryczkowe. Zbalansowane. " +
			"Pan IPAni zostało nagrodzone złotym medalem w Konkursie Piw Rzemieślniczych w Poznaniu w 2015 roku w " +
			"kategorii White IPA. Cieszyliśmy się jak diabli!";

	String AMERICAN_BEAUTY = "Nie chcemy przesadzać z opisem tego piwa. Tak samo zresztą nie chcieliśmy przesadzić z" +
			" samym piwem. " +
			"Warzyliśmy je z Andrzejem Milerem – Grand Championem Birofilia 2013 – największego konkursu piw " +
			"domowych " +
			"w Polsce. Zamysł był bardzo prosty. Wyobraziliśmy sobie piwo “codzienne”, które chcielibyśmy mieć " +
			"zawsze " +
			"w lodówce. Takie, które jest aromatyczne, ale nieprzesadzone, które jest goryczkowe, ale zbalansowane, " +
			"wreszcie takie, które nie powali z nóg, a doda sił i zrelaksuje. " +
			"W aromacie prym wiodą cytrusy, mango i liczi. W smaku lekkie, kruche, umiarkowanie goryczkowe i owocowe" +
			". Bardzo pijalne. Lubimy je.";

	String CALIFA = "Califia to klasyczne amerykańskie India Pale Ale Zachodniego Wybrzeża USA. Jasne, mocno " +
			"wytrawne," +
			" solidnie chmielone i obfite w alkoholową moc. Jej urzekający aromat pochodzi od wspaniałych " +
			"amerykańskich chmieli, dzięki którym odrodziło się amerykańskie piwowarstwo rzemieślnicze, a jego " +
			"wpływy dotarły do Europy. " +
			"Ze szklanki buchną owoce cytrusowe: grejpfruty, pomarańcze, cytryny, limonki oraz przyjemny, słodkawy " +
			"zapach jasnych słodów. " +
			"Chcieliśmy w tym piwie wyrazić ducha Kalifornii, miniojczyzny stylu West Coast IPA. Stąd nazwa – " +
			"Califia" +
			". To jedno z tych piw nad którym pracujemy najmocniej – każda nowa warka przynosi coś nowego. Zależy " +
			" nam na tym, by “wyciągnąć” z amerykańskich chmieli to, co mają w sobie najlepszego – wspaniały, rześki" +
			" aromat do ostatniej możliwej kropli. " +
			"Jeśli lubisz piwa wytrawne, mocne, zdecydowanie goryczkowe i bardzo, bardzo aromatyczne, to Califia " +
			"jest dla Ciebie.";

	String MISTY = "To najciekawszy trend we współczesnej IPA, najbliższy naszym gustom, pokazujący to, co najlepsze" +
			" " +
			"w" +
			" amerykańskim chmielu i amerykańskiej fantazji piwowarskiej. Misty ma szansę zostać ulubionym India " +
			"Pale" +
			" " +
			"Ale w naszej ekipie. Tym razem inspiracją dla nas był dorobek piwowarów z Nowej Anglii w USA. " +
			"Trzy gatunki amerykańskiego chmielu grają w nim pierwsze skrzypce dając soczystą owocowość, zarówno w " +
			"aromacie, jak i w smaku. " +
			"Najpierw natkniesz się na grejpfruty, mango, liczi, limonki, ale też na agrest, białą porzeczkę i – nie" +
			" " +
			"żartujemy – białe wino, a potem dojdzie do Ciebie, bardzo gładka, lekko ziołowa gorycz. To wszystko " +
			"zepnie w całość aksamitna pełnia, pochodząca od pszenicy i owsa, bez których to piwo nie byłoby takie " +
			"samo. " +
			"Współczesna IPA. I jesteśmy na czasie!";

	String PIA = "Na początku jest nowicjuszem pia, by w miarę uczenia się osiągnąć wyższy poziom i stać się tāura w" +
			" " +
			"tradycyjnej szkole ezoterycznej. Oczywiście nie będziemy Wam robić wykładu z kultury maoryskiej, ale " +
			"parę" +
			" słów o cudownych właściwościach nowozelandzkiego chmielu już napiszemy." +
			"W Pia za niecodzienny aromat kokosa, limonki oraz kiwi odpowiedzialne są właśnie chmiele uprawiane w " +
			"odległej Nowej Zelandii. Postanowiliśmy uwarzyć bardzo proste piwo, które będzie wstępem w zapierający " +
			"w" +
			" " +
			"dech w piersiach klimat chmielowych upraw z Kraju Długiej Białej Chmury. Gdy uporacie się już z " +
			"niespodzianką w aromacie i weźmiecie pierwszy łyk zaskoczy Was odczucie soczystości tropikalnych " +
			"owoców," +
			" " +
			"które zakończy krótka przyjemna gorycz, z którą nie chcieliśmy przesadzać. Niska zawartość alkoholu, " +
			"zwłaszcza jak na styl IPA, czyni je piwem wyjątkowo sesyjnym. Oczywiście nie użyliśmy żadnych owoców, " +
			"dodatków ani aromatów, chmiele wykonały dostatecznie dobrą robotę. " +
			"I jeszcze jedno – spróbujcie koniecznie drugiego piwa z nowozelandzkiej serii – Taura – podwójnego IPA." +
			" " +
			"To następny krok wtajemniczenia ?";

	String TAURA = "W tradycyjnej maoryskiej szkole ezoterycznej na początku jesteś nowicjuszem pia, by w miarę " +
			"uczenia się osiągnąć wyższy poziom i stać się tāura. Taura to kolejne nasze piwo, które prezentuje " +
			"zapierający w dech w piersiach klimat chmielowych upraw z Kraju Długiej Białej Chmury. Ma zaskakujący " +
			"aromat kokosa, limonki oraz kiwi, zaś w smaku ocieka potężną soczystością tropikalnych owoców. " +
			"Stonowana" +
			" " +
			"gorycz, imponujące ciało, alkoholu w sam raz, no i znowu – ogromne odczucie owocowości. Oczywiście nie " +
			"użyliśmy żadnych owoców, dodatków ani aromatów. To chmiel wykonał tę wspaniałą robotę." +
			"I jeszcze jedno – spróbujcie koniecznie pierwszego piwa z nowozelandzkiej serii – Pia – sesyjnego IPA. ";

	String TOHUNGA = "Od samego początku chcieliśmy mieć trzy India Pale Ale chmielone znakomitymi odmianami chmieli" +
			" " +
			"z" +
			" dalekiej" +
			" Nowej Zelandii. Pia to w maoryskiej szkole ezoterycznej nowicjusz, taura to już wyższy stopień " +
			"wtajemniczenia, by zaś szczycić się mianem tohunga, trzeba osiągnąć mistrzostwo… " +
			"Tohunga to najmocniejsze piwo z naszego „nowozelandzkiego tryptyku”. Za zapierający dech w " +
			"piersiach aromat kokosa, limonki, kiwi i całej  plejady owoców tropikalnych odpowiedzialne są " +
			"chmiele z Kraju Długiej Białej Chmury. Za ogromną soczystością i cudowną pełnią tego piwa stoją " +
			"płatki pszenne i owsiane oraz specjalnie dobrane drożdże. Gorycz jest niska i krótka, a zawartość" +
			" alkoholu – jak na potrójne IPA – umiarkowana. " +
			"Czy staliśmy się tym piwem tohunga? Sami sobie musicie odpowiedzieć na to pytanie! " +
			"Spróbuj koniecznie pierwszego piwa z nowozelandzkiej serii – Pia – sesyjnego IPA oraz drugiego – " +
			"Taura – podwójnego IPA.";

	String IDIOTA = "Russian Imperial Stout " +
			"Idiota to nasz pierwszy Russian Imperial Stout i jest dokładnie taki jak chcieliśmy – złożony, mocny, " +
			"ale" +
			" jednocześnie gładki, oleisty i super pijalny. Aromat i smak tego piwa to plątanina dorównująca " +
			"złożonością rosyjskiej historii: kawa, czekolada, toffi, lukrecja, orzechy, melasa, runo leśne, ciemne " +
			"suszone owoce, a na dokładkę chmielowa żywiczność. " +
			"Zalecamy pić małymi łyczkami zachowując ostrożność.";


	String SAISONATOR = "Porwaliśmy się na coś naprawdę potężnego. " +
			"Imperialny Saison to nic innego jak super mocna wersja stylu Saison, czyli piwa, które tradycyjnie " +
			"warzone było na farmach w Walonii, francuskojęzycznej części Belgii. Piwo miało nawadniać i krzepić " +
			"sezonowych żniwiarzy. " +
			"Postanowiliśmy wzmocnić ponad trzykrotnie pierwowzór, wsypać masę amerykańskich i polskich chmieli oraz" +
			" " +
			"zadbać o odpowiednio długie leżakowanie. " +
			"Otrzymaliśmy niezmiernie złożone i ultra owocowe piwo o aromacie brzoskwiń, moreli, gruszek, goździków i" +
			" " +
			"ziół. Smak to w zasadzie przedłużenie aromatu zbalansowane odpowiednio wysoką goryczą oraz " +
			"rozgrzewającym" +
			" alkoholem. Pomimo swej mocy Saisonator jest bardzo lekki w odbiorze więc zalecamy brać małe łyczki… " +
			"Takiego piwa na pewno nie podalibyśmy sezonowym pracownikom ?";

}
