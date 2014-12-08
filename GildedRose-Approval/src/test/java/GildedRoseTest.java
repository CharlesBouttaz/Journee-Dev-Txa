import java.util.ArrayList;
import java.util.List;

import org.approvaltests.Approvals;
import org.junit.Test;


public class GildedRoseTest {

	@Test
	public void goldenMasterShouldBeTheSame() throws Exception {
        final GildedRose gildedRose = new GildedRose();
        gildedRose.createItems();

        final List<String> res = new ArrayList<String>();

        for (int i = 0; i < 20; i++) {
            gildedRose.updateQuality();
            res.add(gildedRose.getItems().toString());
        }

        Approvals.verifyAll("Items", res.toArray());
    }

	@Test
	public void externalCallTest() throws Exception {
        final GildedRose gildedRose = new GildedRose();
        gildedRose.createItems();

        final List<String> res = new ArrayList<String>();

        for (int i = 0; i < 3; i++) {
            gildedRose.updateQuality();
            res.add(gildedRose.externalCallParams);
        }

        Approvals.verifyAll("Ordered Items", res.toArray());
    }

    public void parameterisedTests() {
        //LegacyApprovals.LockDown(object, "method", param1Range, param2range);
        // See https://www.youtube.com/watch?v=EJ1tRFEOkcw
    }

    public void binaryCompareTest() {
        // PDF
//        Approvals.verify(byte[])
//        Approvals.verify(inputStream)
    }
}
