---
name: israel-pc-parts-hunter
description: Find the cheapest PC components in Israeli shops and marketplaces (Zap, KSP, Ivory, TMS, Bug, Plonter and others) and assemble a compatible gaming PC build with links, the price of each part and the total price in ILS. Use when the user wants to build or upgrade a computer in Israel, compare component prices in shekels, or asks "собери компьютер", "найди комплектующие", "מחשב גיימינג", "השוואת מחירים".
---

# Israel PC Parts Hunter

Goal: a compatible build in which every part has a working link to an Israeli shop, a price in ₪, and a final total.

## 1. Clarify (only if it is not given)
- Budget in ₪ and target (1080p / 1440p / 4K, which games, streaming/work).
- Leftover parts (monitor, SSD, case), Wi-Fi need, colour/RGB preference.
- No answer → default to a balanced 1440p gaming build and say so.

## 2. Where to search
| Site | What it is | Domain |
|---|---|---|
| Zap | Price comparison across dozens of shops; `model.aspx?modelid=` shows "החל מ-" (from) price and all sellers | zap.co.il |
| KSP | Big chain, often pricier, has "מציאון/עודפים" (outlet) | ksp.co.il |
| Ivory | Big chain, frequent promotions | ivory.co.il |
| TMS | Strong prices on components | tms.co.il |
| Bug | Chain | bug.co.il |
| Plonter | Components shop | plonter.co.il |
| CompMaster, PC Center, Wobbly, Lastprice | Often the cheapest sellers inside Zap | compmaster.co.il etc. |
| Amazon / AliExpress | Only as a comparison: orders over $75 pay VAT (and customs), warranty is harder | — |

## 3. How to search (important: tool limits)
- In this cloud environment the Israeli sites are usually **blocked for direct fetch** (curl/WebFetch get DNS/403). Check once; if blocked, do not retry.
- What works: `WebSearch` with `mode: "extended"` and `allowed_domains` = the list of Israeli shops above. The search summary reads prices off the shops' pages.
- Write queries **in Hebrew** plus the exact model name: `כרטיס מסך RTX 5070 מחיר`, `לוח אם B650 מחיר`, `זיכרון DDR5 32GB 6000 CL30 מחיר`, `ספק כוח 750W Gold`, `מארז מחשב`, `קירור מעבד`, `כונן SSD NVMe 2TB`.
- For each category: first a broad query (to see prices across the market), then a targeted one on the chosen model (for the product link and the "from" price on Zap).
- Run independent searches in parallel, in a single turn.

## 4. Price traps (always check)
- **"המחירים בתוקף עד DD/MM/YYYY"** on Ivory/TMS: if the date has already passed, the price is out of date. Mark it or pick another source.
- **"מחיר באילת"** (Eilat price) is lower because Eilat has no VAT. It applies only to purchase/pickup in Eilat. Do not count it as the main price.
- **"לא זמין / אזל"** (out of stock): do not put it in the build.
- **Box vs Tray**: Tray has no box/cooler and sometimes a shorter warranty.
- **"מציאון / מוחדש"** (outlet / refurbished): mention separately, do not put in the main build.
- Shipping cost: Zap shows "משלוח חינם" (free shipping) or the shipping price; account for it.
- Search result prices are a snapshot, not live. Always tell the user to check the price at checkout.

## 5. Compatibility (check before the final list)
- CPU socket = motherboard socket (AM5 ↔ B650/B850/X870; LGA1851 ↔ B860/Z890).
- RAM type matches the board (DDR5); for AM5 the sweet spot is 6000 MT/s CL30 with an EXPO profile.
- Cooler supports the socket; Tray/X3D processors have no stock cooler.
- PSU: GPU TDP + CPU + ~150 W headroom (RTX 5070 / RX 9070 XT → 750 W Gold).
- GPU length and cooler height fit the case; ATX board → ATX case.

## 6. Default build priorities (gaming)
1. GPU: ~35–45% of the budget.
2. CPU: Ryzen 7 X3D is the best for games; if the budget is tight, Ryzen 5 7600/9600.
3. 32 GB DDR5, an NVMe SSD of at least 1 TB (2 TB is better for games).
4. Do not skimp on the PSU (a known brand, 80+ Gold).
5. Choose the board by price/features without overpaying for chipset X870 in a gaming build.

## 7. Output format
A table: `# | Component | Model | Shop | Price ₪ | Link`, then **Total**.
Below it: 1–2 alternatives (cheaper / stronger with the price difference), the compatibility check, and the warnings (prices out of date, Eilat price, stock). Answer in the user's language.
