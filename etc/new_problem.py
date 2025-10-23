import argparse

LANGS = ["python", "java"]
TEMPLATE_FILES = {
    "java": "java/src/main/java/net/tcurt/sandbox/DefaultProblem.java",
    "python": "python/template/default_problem.py",
}
TEMPLATE_TEST_FILES = {
    "java": "java/src/test/java/net/tcurt/sandbox/DefaultProblemTest.java",
    "python": "python/tests/test_default_problem.py",
}
LOCATIONS = {
    "java": {
        "leetcode": "java/src/main/java/net/tcurt/sandbox/leetcode",
        "mock": "java/src/main/java/net/tcurt/sandbox/interviews/mock",
        "interviews": "java/src/main/java/net/tcurt/sandbox/interviews",
    },
    "python": {
        "leetcode": "python/leetcode",
        "mock": "python/interviews/mock",
        "interviews": "python/interviews",
    },
}
JAVA_PACKAGES = {
    "leetcode": "net.tcurt.sandbox.leetcode",
    "mock": "net.tcurt.sandbox.interviews.mock",
    "interviews": "net.tcurt.sandbox.interviews",
}


def init_problem(args):
    template_file = TEMPLATE_FILES[args.lang]
    dest = LOCATIONS[args.lang][args.type]
    suffix = ".java" if args.lang == "java" else ".py"
    filename = f"{dest}/{args.problem_name}{suffix}"

    with open(template_file, "r", encoding="utf-8") as fin, open(
        filename, "w", encoding="utf-8"
    ) as fout:
        print(f"Initializing {args.type} solution code in {args.lang}: {filename}")
        for line in fin:
            out = line.replace("DefaultProblem", args.problem_name)
            if line.startswith("package"):
                out = "package " + JAVA_PACKAGES[args.type] + ";\n"
            if args.number:
                out = out.replace("LEETCODE_NUMBER", args.number)
            if args.link:
                out = out.replace("LEETCODE_LINK", args.link)

            fout.write(out)

    test_template_file = TEMPLATE_TEST_FILES[args.lang]
    if args.lang == "java":
        test_dest = dest.replace("main", "test")
        test_filename = f"{test_dest}/{args.problem_name}Test.java"
    else:
        test_dest = "python/tests"
        test_filename = f"{test_dest}/test_{args.problem_name}.py"

    with open(test_template_file, "r", encoding="utf-8") as fin, open(
        test_filename, "w", encoding="utf-8"
    ) as fout:
        print(f"Initializing {args.type} test     code in {args.lang}: {test_filename}")
        for line in fin:
            out = line.replace("DefaultProblem", args.problem_name)
            out = line.replace(
                "template.default_problem", f"{args.type}.{args.problem_name}"
            )
            if line.startswith("package"):
                out = "package " + JAVA_PACKAGES[args.type] + ";\n"
            fout.write(out)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Automates quickly creating a new sandbox problem (and corresponding test).",
        formatter_class=argparse.ArgumentDefaultsHelpFormatter,
    )

    parser.add_argument(
        "--lang",
        choices=LANGS,
        default="python",
        help="Language to use for the new problem",
    )

    parser.add_argument("problem_name", type=str, help="Used to name the files")

    parser.add_argument(
        "--type",
        choices=LOCATIONS["java"].keys(),
        default="leetcode",
        help="New problem's type",
    )

    parser.add_argument(
        "--number", help="The leetcode number associated with the new problem"
    )

    parser.add_argument(
        "--link", help="The leetcode link associated with the new problem"
    )

    init_problem(parser.parse_args())
