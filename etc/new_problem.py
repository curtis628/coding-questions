import argparse

TEMPLATE_FILE = "src/main/java/net/tcurt/sandbox/DefaultProblem.java"
TEMPLATE_TEST_FILE = "src/test/java/net/tcurt/sandbox/DefaultProblemTest.java"
LOCATIONS = {
    "leetcode": "src/main/java/net/tcurt/sandbox/leetcode",
    "mock": "src/main/java/net/tcurt/sandbox/interviews/mock",
    "interviews": "src/main/java/net/tcurt/sandbox/interviews",
}
PACKAGES = {
    "leetcode": "net.tcurt.sandbox.leetcode",
    "mock": "net.tcurt.sandbox.leetcode.interviews.mock",
    "interviews": "net.tcurt.sandbox.leetcode.interviews",
}

def init_problem(args):
    dest = LOCATIONS[args.type]
    filename = f"{dest}/{args.problem_name}.java"

    test_dest = dest.replace("main", "test")
    test_filename = f"{test_dest}/{args.problem_name}Test.java"

    with open(TEMPLATE_FILE, "r", encoding="utf-8") as fin, \
         open(filename, "w", encoding="utf-8") as fout:
        print(f"Initializing {args.type} solution code: {filename}")
        for line in fin:
            out = line.replace("DefaultProblem", args.problem_name)
            if line.startswith("package"):
                out = "package " + PACKAGES[args.type] + ";\n"
            if args.number:
                out = out.replace("LEETCODE_NUMBER", args.number)
            if args.link:
                out = out.replace("LEETCODE_LINK", args.link)

            fout.write(out)

    with open(TEMPLATE_TEST_FILE, "r", encoding="utf-8") as fin, \
         open(test_filename, "w", encoding="utf-8") as fout:
        print(f"Initializing {args.type} test code:     {test_filename}")
        for line in fin:
            out = line.replace("DefaultProblem", args.problem_name)
            if line.startswith("package"):
                out = "package " + PACKAGES[args.type] + ";\n"
            fout.write(out)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description="Automates quickly creating a new sandbox problem (and corresponding test).",
        formatter_class=argparse.ArgumentDefaultsHelpFormatter
    )

    parser.add_argument(
        "problem_name",
        type=str,
        help="Used to name the java files"
    )

    parser.add_argument(
        "--type",
        choices=LOCATIONS.keys(),
        default="leetcode",
        help="New problem's type"
    )

    parser.add_argument(
        "--number",
        help="The leetcode number associated with the new problem"
    )

    parser.add_argument(
        "--link",
        help="The leetcode link associated with the new problem"
    )

    init_problem(parser.parse_args())

