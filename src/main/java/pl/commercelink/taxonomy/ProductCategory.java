package pl.commercelink.taxonomy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ProductCategory {

    CPU(ProductGroup.PcComponents),
    Cooler(ProductGroup.PcComponents),
    GPU(ProductGroup.PcComponents),
    Motherboard(ProductGroup.PcComponents),
    PSU(ProductGroup.PcComponents),
    Storage(ProductGroup.PcComponents),
    Memory(ProductGroup.PcComponents),
    Case(ProductGroup.PcComponents),
    Fan(ProductGroup.PcComponents),
    ModdingPC(ProductGroup.PcComponents),
    Other(ProductGroup.PcComponents),

    Services(ProductGroup.Services),

    Laptops(ProductGroup.Computers),
    Desktops(ProductGroup.Computers),
    Workstations(ProductGroup.Computers),
    Servers(ProductGroup.Computers),
    AllInOnePCs(ProductGroup.Computers),
    GraphicsTablets(ProductGroup.Computers),
    Software(ProductGroup.Computers),

    Smartphones(ProductGroup.SmartphonesTablets),
    StationaryPhones(ProductGroup.SmartphonesTablets),
    Tablets(ProductGroup.SmartphonesTablets),
    SmartphoneCases(ProductGroup.SmartphonesTablets),
    ScreenProtectors(ProductGroup.SmartphonesTablets),
    Chargers(ProductGroup.SmartphonesTablets),
    Powerbanks(ProductGroup.SmartphonesTablets),
    MobileHeadphones(ProductGroup.SmartphonesTablets),

    Printers(ProductGroup.PrintersScanners),
    LaserPrinters(ProductGroup.PrintersScanners),
    InkPrinters(ProductGroup.PrintersScanners),
    PhotoPrinters(ProductGroup.PrintersScanners),
    LargeFormatPrinters(ProductGroup.PrintersScanners),
    LabelPrinters(ProductGroup.PrintersScanners),
    Printers3D(ProductGroup.PrintersScanners),
    Scanners(ProductGroup.PrintersScanners),
    MultifunctionPrinters(ProductGroup.PrintersScanners),

    Displays(ProductGroup.Peripherals),
    Keyboards(ProductGroup.Peripherals),
    Mice(ProductGroup.Peripherals),
    KeyboardsAndMice(ProductGroup.Peripherals),
    Headphones(ProductGroup.Peripherals),
    Microphones(ProductGroup.Peripherals),
    Webcams(ProductGroup.Peripherals),
    Speakers(ProductGroup.Peripherals),
    MousePads(ProductGroup.Peripherals),

    GamingChairs(ProductGroup.Furniture),
    OfficeChairs(ProductGroup.Furniture),
    GamingDesks(ProductGroup.Furniture),
    OfficeDesks(ProductGroup.Furniture),
    StandingDesks(ProductGroup.Furniture),
    MonitorMounts(ProductGroup.Furniture),
    Footrests(ProductGroup.Furniture);

    private final ProductGroup productGroup;

    ProductCategory(ProductGroup productGroup) { this.productGroup = productGroup; }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public boolean eq(String name) {
        return this.name().equalsIgnoreCase(name);
    }

    public static List<ProductCategory> values(ProductGroup productGroup) {
        return Arrays.stream(values()).filter(v -> v.productGroup == productGroup).collect(Collectors.toList());
    }

    public static List<ProductCategory> values(List<ProductGroup> productGroups) {
        return Arrays.stream(values())
                .filter(v -> productGroups.contains(v.productGroup))
                .collect(Collectors.toList());
    }
}
