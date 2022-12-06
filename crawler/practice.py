import googletrans

translator = googletrans.Translator()

aa = "'https://www.quora.com/Is-it-okay-to-store-telephone-numbers-as-integer-data-types-since-telephone-numbers-can-sometimes-be-written-in-digits-only-format-such-as-2345678901'"

print(len(aa))

res = translator.translate(aa, dest='ko', src='en')

print(f"{aa} => {res.text}")

# https://lolmeme.quora.com/Which-screenshot-deserves-0-01-million-views-3
# https://www.quora.com/Who-are-some-criminals-you-have-the-most-respect-for

a = "'https://www.quora.com/Who-are-some-criminals-you-have-the-most-respect-for"
b = 'https://lolmeme.quora.com/Which-screenshot-deserves-0-01-million-views-3'

a = a[9:22]
b = b[8:21]

# print(a)
# print(b)