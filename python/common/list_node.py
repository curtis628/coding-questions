class ListNode:
    """LeetCode definition for singly-linked list"""

    @classmethod
    def from_list(cls, values):
        """Builds a linked list from a Python list of ints."""
        dummy = curr = cls(-1)
        for v in values:
            curr.next = cls(v)
            curr = curr.next
        return dummy.next

    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __eq__(self, other):
        """Deep equality check for linked lists."""
        if not isinstance(other, ListNode):
            return False
        a, b = self, other
        while a and b:
            if a.val != b.val:
                return False
            a, b = a.next, b.next
        return a is None and b is None

    def __repr__(self):
        """Readable string representation for test output."""
        vals = []
        curr = self
        while curr:
            vals.append(str(curr.val))
            curr = curr.next
        return "ListNode(" + "->".join(vals) + ")"
