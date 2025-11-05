import pytest

from common.method import Method
from leetcode.concat_all_words_substring import Solution


@pytest.mark.parametrize(
    "s, words, expected",
    [
        ("barfoothefoobarman", ["foo", "bar"], {0, 9}),
        ("wordgoodgoodgoodbestword", ["word", "good", "best", "word"], set()),
        ("wordgoodgoodgoodbestword", ["word", "good", "best", "good"], {8}),
        ("barfoofoobarthefoobarman", ["bar", "foo", "the"], {6, 9, 12}),
        ("abcdyz", ["ab", "cd", "ef"], set()),
        ("a", ["a"], {0}),
        ("aaaaaaaaaaaaaa", ["aa", "aa"], {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
    ],
)
@pytest.mark.parametrize(
    "method",
    [Method.BRUTE_FORCE, Method.OPTIMAL],
)
def test_basic(s, words, expected, method):
    solver = Solution(method)
    assert set(solver.find_substring(s, words)) == expected


# fmt: off
@pytest.mark.parametrize(
    "s, words, expected",
    [
        (
            "fffffffffffffffffffffffffffffffff",
            [
                "a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
                "a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
            ],
            set()
        ),
        (
            "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbs"
            "zgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnj"
            "ycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjr"
            "gulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkq"
            "unuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsa"
            "rqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqap"
            "oojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvu"
            "gevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaa"
            "cgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwz"
            "qkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbg"
            "eqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryu"
            "qixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxq"
            "imkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluyp"
            "wrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxetesh"
            "yrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmk"
            "vkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel",
            [
                "dhvf", "sind", "ffsl", "yekr", "zwzq", "kpeo",
                "cila", "tfty", "modg", "ztjg", "ybty", "heqg",
                "cpwo", "gdcj", "lnle", "sefg", "vimw", "bxcb",
            ],
            {935}
        ),
    ],
)
@pytest.mark.parametrize(
    "method",
    [Method.OPTIMAL],  # only OPTIMAL will run these large tests
)
# fmt: on
def test_tricky(s, words, expected, method):
    solver = Solution(method)
    assert set(solver.find_substring(s, words)) == expected
